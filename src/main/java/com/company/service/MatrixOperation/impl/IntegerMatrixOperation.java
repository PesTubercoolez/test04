package com.company.service.MatrixOperation.impl;

import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.MatrixOperation.MatrixOperation;

public class IntegerMatrixOperation implements MatrixOperation {

    public Matrix multiplyMatrix(Matrix firstMatrix, Matrix secondMatrix) {

        if (firstMatrix.getRows() == secondMatrix.getColumns()) {

            IntegerMatrix thirdMatrix = new IntegerMatrix(firstMatrix.getRows(), secondMatrix.getColumns());

            for (int i = 0; i < firstMatrix.getRows(); i++) {
                for (int j = 0; j < secondMatrix.getColumns(); j++) {
                    for (int k = 0; k < firstMatrix.getColumns(); k++) {
                        thirdMatrix.setValue(i ,j, thirdMatrix.getValue(i,j).intValue() + firstMatrix.getValue(i, k).intValue() * secondMatrix.getValue(k, j).intValue());
                    }
                }
            }

            return thirdMatrix;
        } else {
            System.out.println("You cannot multiply such types of matrix (The quantity of columns in the first matrix must be equal to the quantity of rows in the second)"+ firstMatrix.getSize() + " " + secondMatrix.getSize() + "\n");

            return firstMatrix;
        }
    }
}
