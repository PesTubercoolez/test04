package com.company.service.MatrixOperation.impl;

import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.MatrixOperation.MatrixOperation;

public class IntegerMatrixOperation implements MatrixOperation {

    public Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2) {

        if (matrix1.getRows() == matrix2.getColumns() || matrix1.getColumns() == matrix2.getRows()) {

            IntegerMatrix matrix3 = new IntegerMatrix(matrix1.getRows(), matrix2.getColumns());

            int[][] arr = matrix3.getMatrix();

            for (int i = 0; i < matrix1.getRows(); i++) {

                for (int j = 0; j < matrix2.getColumns(); j++) {

                    for (int k = 0; k < matrix1.getColumns(); k++) {

                        arr[i][j] += matrix1.getValue(i, k).intValue() * matrix2.getValue(k, j).intValue();
                    }
                }
            }

            matrix3.setAllValues(arr);

            return matrix3;

        } else {

            System.out.println("You cannot multiply such types of matrix (The quantity of columns in the first matrix must be equal to the quantity of rows in the second)" + "\n");

            return matrix1;

        }
    }
}

