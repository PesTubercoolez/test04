package com.company.factory;

import com.company.model.IntegerMatrix;

public class MatrixOperation {

    IntegerMatrix matrix;

    public void multiplyMatrix(int[][] arr1, int[][] arr2) {

        if (arr1.length == arr2[0].length || arr1[0].length == arr2.length) {

            int[][] arr3 = new int[arr1.length][arr2[0].length];

            for (int x = 0; x < arr3.length; x++) {

                for (int j = 0; j < arr3[0].length; j++) {

                    for (int k = 0; k < arr1[0].length; k++) {

                        arr3[x][j] += arr1[x][k] * arr2[k][j];

                    }
                }
            }
            matrix = new IntegerMatrix(arr3);

            matrix.showMatrix();

        } else {

            System.out.println("You cannot multiply such types of matrix (The quantity of columns in the first matrix must be equal to the quantity of rows in the second)");

        }
    }
}

