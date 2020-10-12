package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class MatrixOperation {

    public void multiplyMatrix(int[][] arr1, int[][] arr2) {

        if (arr1 [0].length == arr2.length) {

            int[][] arr3 = new int[arr1.length][arr1.length];

            for (int x = 0; x < arr3.length; x++) {

                for (int j = 0; j < arr3.length; j++) {

                    for (int k = 0; k < arr3.length; k++) {

                        arr3[x][j] += arr1[x][k] * arr2[k][j];

                    }
                }
            }

        showMatrix(arr3);

        }else {

            System.out.println("You cannot multiply such types of matrix (The quantity of columns in the first matrix must be equal to the quantity of rows in the second)");

        }
    }

    private void showMatrix (int [] [] arr) {

        for (int x = 0; x < arr.length; x++){

            for (int j = 0; j < arr [0].length; j++){

                System.out.print(arr [x] [j] + "\t");

            }

            System.out.println();

        }
    }
}

