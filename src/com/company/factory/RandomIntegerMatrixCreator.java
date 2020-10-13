package com.company.factory;

import com.company.factory.MatrixCreator;
import com.company.model.IntegerMatrix;

import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerMatrixCreator extends MatrixCreator {

    IntegerMatrix matrix;

    @Override
    public IntegerMatrix createMatrix() {

        matrix = new IntegerMatrix(3, 3);

        int [] [] arr = new int [3] [3];

            for (int x = 0; x < arr.length; x++) {

                for (int k = 0; k < arr.length; k++) {

                    arr[x][k] = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                }
            }

            matrix.setValue(arr);

            matrix.showMatrix();

            return matrix;
        }
}
