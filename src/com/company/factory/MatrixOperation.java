package com.company.factory;

import com.company.model.IntegerMatrix;

public class MatrixOperation {

    public IntegerMatrix multiplyMatrix(IntegerMatrix matrix1, IntegerMatrix matrix2) {

        if (matrix1.getRows() == matrix2.getColumns()) {

           IntegerMatrix matrix3 = new IntegerMatrix(matrix1.getRows(), matrix2.getColumns());

            int [][] arr = matrix3.getMatrix();

            for (int i = 0; i < matrix1.getRows(); i++) {

                for (int j = 0; j < matrix2.getColumns(); j++) {

                    for (int k = 0; k < matrix1.getColumns(); k++) {

                      arr [i] [j] += matrix1.getValue(i, k) * matrix2.getValue(k,j);

                    }
                }
            }

            matrix3.setAllValues(arr);

            return matrix3;

        } else {

            System.out.println("You cannot multiply such types of matrix (The quantity of columns in the first matrix must be equal to the quantity of rows in the second)");

            return matrix1;

        }
    }
}

