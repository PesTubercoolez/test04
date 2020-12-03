package com.company.service.MatrixOperation.impl;

import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.MatrixOperation.MatrixOperation;

import java.util.Arrays;

public class IntegerMatrixOperation implements MatrixOperation {

    public Matrix multiplyMatrix(Matrix firstMatrix, Matrix secondMatrix) {

        if (firstMatrix.getRows() == secondMatrix.getColumns()) {

            IntegerMatrix thirdMatrix = new IntegerMatrix(firstMatrix.getRows(), secondMatrix.getColumns());

            int[][] resultArray = new int[firstMatrix.getRows()][secondMatrix.getColumns()];

            for (int i = 0; i < firstMatrix.getRows(); i++) {
                for (int j = 0; j < secondMatrix.getColumns(); j++) {
                    for (int k = 0; k < firstMatrix.getColumns(); k++) {
                        resultArray[i][j] += firstMatrix.getValue(i, k).intValue() * secondMatrix.getValue(k, j).intValue();
                    }
                }
            }
            thirdMatrix.setAllValues(resultArray);

            return thirdMatrix;

        } else {
            System.out.println("You cannot multiply such types of matrix (The quantity of columns in the first matrix must be equal to the quantity of rows in the second)" + firstMatrix.getRows() + " " + secondMatrix.getColumns() + "\n");
            firstMatrix.showMatrix();
            secondMatrix.showMatrix();

            return firstMatrix;
        }
    }

    public Number[] multiplyMatrixWithVectors(Matrix firstMatrix, Matrix secondMatrix, int position) {

        if (firstMatrix.getRows() == secondMatrix.getColumns()) {
            int result;
            Number[] resultArray = new Number[secondMatrix.getRows()];
            Arrays.fill(resultArray, 0);

            for (int x = 0; x < secondMatrix.getColumns(); x++) {
                result = 0;
                for (int k = 0; k < firstMatrix.getColumns(); k++) {
                    result += firstMatrix.getVectorValue(position, k, "row").intValue() *
                            secondMatrix.getVectorValue(x, k, "column").intValue();
                }
                resultArray[x] = result;
            }

            return resultArray;

        } else {
            System.out.println("You cannot multiply this vectors " + firstMatrix.getColumns() + " " + secondMatrix.getRows());
            return firstMatrix.getRowVector(0);
        }
    }
}
