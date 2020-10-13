package com.company.factory;

import com.company.model.IntegerMatrix;

import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerMatrixCreator implements MatrixCreator {

    IntegerMatrix matrix;

    @Override
    public IntegerMatrix createMatrix() {

        int l;

        matrix = new IntegerMatrix();

            for (int x = 0; x < matrix.getRows(); x++) {

                for (int k = 0; k < matrix.getColumns(); k++) {

                    l = ThreadLocalRandom.current().nextInt(0, 9 + 1);

                    matrix.setValue(x,k,l);
                }
            }

            return matrix;
    }
}
