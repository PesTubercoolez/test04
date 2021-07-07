package com.company;

import com.company.DataBaseConstants.DataBaseConstants;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.DataBaseHandler.impl.PostgresHandler;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.VectorIntegerMatrixMultiplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        //Creating connection and DB handler
        Connection connection = DriverManager.getConnection(DataBaseConstants.URL, DataBaseConstants.USER, DataBaseConstants.PASSWORD);
        PostgresHandler<Matrix> dbHandler = new PostgresHandler<>();

        //Inserting result matrix in database
        dbHandler.insertJSONInResult(enhancedThreadMultiplyer(getMatrixFromDB(dbHandler, DataBaseConstants.SELECT_FIRST_MATRIX_FROM_MATRIX_STORAGE, connection),
                getMatrixFromDB(dbHandler, DataBaseConstants.SELECT_SECOND_MATRIX_FROM_MATRIX_STORAGE, connection)), connection);

        //Creating new matrix from result_storage table in database
        getMatrixFromDB(dbHandler, DataBaseConstants.SELECT_FROM_RESULT_STORAGE, connection);
        connection.close();
    }

    private static Matrix enhancedThreadMultiplyer(Matrix firstMatrix, Matrix secondMatrix) throws Exception {
        Matrix thirdMatrix = new IntegerMatrix(firstMatrix.getRows(), secondMatrix.getColumns());
        ExecutorService executor = Executors.newFixedThreadPool(8);
        Future<Number[]> futureVector;
        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();

        for (int x = 0; x < firstMatrix.getRows(); x++) {
            futureVector = executor.submit(new VectorIntegerMatrixMultiplication(firstMatrix, secondMatrix, x));
            filler.fillMatrixFromVector(thirdMatrix, futureVector.get(), x);
        }
        executor.shutdown();

        return thirdMatrix;
    }

    private static Matrix getMatrixFromDB(PostgresHandler<Matrix> crud, String outStatement, Connection connection) throws SQLException {
        return new CustomIntegerMatrixCreator().convertMatrixFromJson(crud.selectJSON(outStatement, connection, 1));
    }
}
