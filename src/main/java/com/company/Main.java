package com.company;

import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.VectorIntegerMatrixMultiplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {


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
        thirdMatrix.showMatrix();
        return thirdMatrix;
    }
}
