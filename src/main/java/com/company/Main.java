package com.company;

import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.model.Result;
import com.company.model.impl.IntegerMatrix;
import com.company.service.DataBaseHandler.RDBCRUD;
import com.company.service.DataBaseHandler.impl.PostgresHandler;
import com.company.service.FileHandler.FileHandler;
import com.company.service.FileHandler.impl.XLSXFileHandler;
import com.company.service.FileParser.FileParser;
import com.company.service.FileParser.impl.XLSXFileParser;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.VectorIntegerMatrixMultiplication;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "root";
        String password = "zetaprime";
        String insertStatement = "INSERT INTO matrix_storage (first_matrix, second_matrix) VALUES (to_json(?::json), to_json(?::json))";
//        String insertResultStatement = "INSERT INTO result_storage (result_matrix) VALUES (to_json(?::json))";
        String returnResultStatement = "SELECT result_matrix FROM result_storage";
        PostgresHandler <Matrix> dbHandler = new PostgresHandler<>(url,user,password);
        String readPath = FilePathConstants.LINUX_ABSOLUTE_FILE_READ_PATH;
        FileParser parser = new XLSXFileParser();
        XLSXFileHandler fileHandler = new XLSXFileHandler();
        Matrix firstMatrix = readFile(parser, readPath, 0, fileHandler);
        Matrix secondMatrix = readFile(parser, readPath, 1, fileHandler);
        Matrix thirdMatrix = enhancedThreadMultiplyer(firstMatrix,secondMatrix);
         model ynhn = insertMatrixInDB(dbHandler, insertStatement,firstMatrix,secondMatrix);
        Result vrt4v = new Result();
        vrt4v.setId(WEFCERF);
//        select these matrix

//        make result

//        save in result db

//        select result



        insertMatrixInDB(dbHandler, insertResultStatement,thirdMatrix);
        Matrix forthMatrix = getMatrixFromDB(dbHandler,returnResultStatement, 1);
        forthMatrix.showMatrix();
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
    private static Matrix getMatrixFromDB(PostgresHandler<Matrix> crud, String outStatement, int neededColumn) throws SQLException {
        return new CustomIntegerMatrixCreator().convertMatrixFromJson(crud.selectJSON(outStatement,neededColumn));
    }
    private static void insertMatrixInDB(PostgresHandler<Matrix> crud, String inStatement, Matrix... matrix) throws SQLException{
        crud.insertJSON(inStatement,matrix);
    }
}
