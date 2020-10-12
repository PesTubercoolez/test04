package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerMatrixCreator extends MatrixCreator {

    @Override
    public int [] [] createMatrix() {

        int [] [] arr = new int [3] [3];

            for (int x = 0; x < arr.length; x++) {

                for (int k = 0; k < arr.length; k++) {

                    arr[x][k] = ThreadLocalRandom.current().nextInt(0, 9 + 1);

                }
            }
            super.showMatrix(arr);

            return arr;
        }
}
