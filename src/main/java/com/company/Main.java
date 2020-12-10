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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
        //Objects for handling files in case of getting new matrixes from them and setting to database
        String readPath = FilePathConstants.LINUX_ABSOLUTE_FILE_READ_PATH;
        FileParser parser = new XLSXFileParser();
        XLSXFileHandler fileHandler = new XLSXFileHandler();
        /*
        Creating connection and database handler objects for establishing connection between Java and PostgresSQL and
        creating matrix objects via taking them from matrix_storage table in database in JSON format and conversion them to matrix
         */
        Connection connection = DriverManager.getConnection(DataBaseConstants.URL, DataBaseConstants.USER, DataBaseConstants.PASSWORD);
        PostgresHandler<Matrix> dbHandler = new PostgresHandler<>();
        Matrix firstMatrix = getMatrixFromDB(dbHandler, DataBaseConstants.SELECT_FIRST_MATRIX_FROM_MATRIX_STORAGE, 1, connection);
        Matrix secondMatrix = getMatrixFromDB(dbHandler, DataBaseConstants.SELECT_SECOND_MATRIX_FROM_MATRIX_STORAGE, 1, connection);
        /*
        Calling the method that multiply two matrixes and return result in matrix object and
        calling the method that converts matrix in JSON format and setting it in database
         */
        Matrix thirdMatrix = enhancedThreadMultiplyer(firstMatrix, secondMatrix);
        dbHandler.insertJSONInResult(thirdMatrix, connection);
        //Creating new matrix from result_storage table in database
        Matrix forthMatrix = getMatrixFromDB(dbHandler, DataBaseConstants.SELECT_FROM_RESULT_STORAGE, 1, connection);
        writeFile(fileHandler, parser, FilePathConstants.LINUX_ABSOLUTE_FILE_WRITE_PATH, forthMatrix);
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
        handler.writeFile(handler.createFile(path), parser.parseVariablesToList(resultMatrix));
    }

    private static Matrix readFile(FileParser parser, String path, int numberOfSheet, FileHandler handler) throws IOException, ZeroInputException {
        File file = handler.createFile(path);
        Matrix matrix = new CustomIntegerMatrixCreator().createMatrixFromFile(parser.readFromFile(file, numberOfSheet));
        new CustomIntegerMatrixFiller().fillMatrixFromList(matrix, parser.readFromFile(file, numberOfSheet));

        return matrix;
    }

    private static Matrix getMatrixFromDB(PostgresHandler<Matrix> crud, String outStatement, int neededColumn, Connection connection) throws SQLException {
        return new CustomIntegerMatrixCreator().convertMatrixFromJson(crud.selectJSON(outStatement, connection, neededColumn));
    }

    private static void insertMatrixInDB(PostgresHandler<Matrix> crud, String inStatement, Connection connection, Matrix... matrix) throws SQLException {
        crud.insertJSON(inStatement, connection, matrix);
    }
}
