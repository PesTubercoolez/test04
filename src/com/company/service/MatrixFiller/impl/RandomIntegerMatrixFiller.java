package com.company.service.MatrixFiller.impl;

import com.company.model.Matrix;
import com.company.service.MatrixFiller.MatrixFiller;

import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerMatrixFiller implements MatrixFiller {


    @Override
    public Matrix fillMatrix(Matrix matrix) {

        for (int x = 0; x < matrix.getRows(); x++) {

            for (int k = 0; k < matrix.getColumns(); k++) {
                matrix.setValue(x, k, ThreadLocalRandom.current().nextInt(0, 9 + 1));
            }
        }

        return matrix;
    }
}
