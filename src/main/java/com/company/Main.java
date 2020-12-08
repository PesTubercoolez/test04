package com.company;

import com.company.constants.DataBaseConstants.DataBaseConstants;
import com.company.constants.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.DataBaseHandler.impl.PostgresHandler;
import com.company.service.FileHandler.FileHandler;
import com.company.service.FileHandler.impl.XLSXFileHandler;
import com.company.service.FileParser.FileParser;
import com.company.service.FileParser.impl.XLSXFileParser;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.VectorIntegerMatrixMultiplication;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {

        /*
        I left objects and methods that is used for file handling in case of adding new matrixes to DB
         */

        String readPath = FilePathConstants.LINUX_ABSOLUTE_FILE_READ_PATH;
        FileParser parser = new XLSXFileParser();
        XLSXFileHandler fileHandler = new XLSXFileHandler();

        /*
        Creating connection between Java and Postgress where I get matrixes from DB,
        do multiplication procedure, insert the result in DB and return it back
         */

        Connection connection = DriverManager.getConnection(DataBaseConstants.URL, DataBaseConstants.USER,DataBaseConstants.PASSWORD);
        PostgresHandler <Matrix> dbHandler = new PostgresHandler<>();
        Matrix firstMatrix = getMatrixFromDB(dbHandler,DataBaseConstants.SELECT_FIRST_MATRIX_FROM_MATRIX_STORAGE ,1, connection);
        Matrix secondMatrix = getMatrixFromDB(dbHandler,DataBaseConstants.SELECT_SECOND_MATRIX_FROM_MATRIX_STORAGE,1,connection);
        Matrix thirdMatrix = enhancedThreadMultiplyer(firstMatrix,secondMatrix);
        dbHandler.insertJSONInResult(thirdMatrix,connection);
        Matrix forthMatrix = getMatrixFromDB(dbHandler,DataBaseConstants.SELECT_FROM_RESULT_STORAGE,1,connection);
        connection.close();
    }

    private static Matrix enhancedThreadMultiplyer(Matrix firstMatrix, Matrix secondMatrix) throws Exception {
        Matrix thirdMatrix = new IntegerMatrix(firstMatrix.getRows(), secondMatrix.getColumns());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Number[]> futureVector;
        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();

        for (int x = 0; x < firstMatrix.getRows(); x++) {
            futureVector = executor.submit(new VectorIntegerMatrixMultiplication(firstMatrix, secondMatrix, x));
            filler.fillMatrixFromVector(thirdMatrix, futureVector.get(), x);
        }
        executor.shutdown();

        return thirdMatrix;
    }

    private static void writeFile(FileHandler handler, FileParser parser, String path, Matrix resultMatrix) throws IOException {
        File file = handler.createFile(path);
        handler.writeFile(file, parser.parseVariablesToList(resultMatrix));
    }

    private static Matrix readFile(FileParser parser, String path, int numberOfSheet, FileHandler handler) throws IOException, ZeroInputException {
        File file = handler.createFile(path);
        CustomIntegerMatrixCreator creator = new CustomIntegerMatrixCreator();
        Matrix matrix = creator.createMatrixFromFile(parser.readFromFile(file, numberOfSheet));
        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();
        filler.fillMatrixFromList(matrix, parser.readFromFile(file, numberOfSheet));

        return matrix;
    }

    private static Matrix getMatrixFromDB(PostgresHandler<Matrix> crud, String outStatement, int neededColumn, Connection connection) throws SQLException {
        return new CustomIntegerMatrixCreator().convertMatrixFromJson(crud.selectJSON(outStatement,connection,neededColumn));
    }

    private static void insertMatrixInDB(PostgresHandler<Matrix> crud, String inStatement, Connection connection, Matrix... matrix) throws SQLException{
        crud.insertJSON(inStatement,connection,matrix);
    }
}
