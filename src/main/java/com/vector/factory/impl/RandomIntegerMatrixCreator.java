package com.vector.factory.impl;

import com.vector.exception.ZeroInputException;
import com.vector.factory.MatrixCreator;
import com.vector.model.Matrix;
import com.vector.model.impl.IntegerMatrix;
import com.vector.service.MatrixFiller.impl.RandomIntegerMatrixFiller;


public class RandomIntegerMatrixCreator implements MatrixCreator {

    @Override
    public Matrix createMatrix(int rows, int columns) throws ZeroInputException {

        RandomIntegerMatrixFiller randFiller = new RandomIntegerMatrixFiller();
        IntegerMatrix matrix = new IntegerMatrix(rows, columns);

        if (rows == 0 || columns == 0) {
            throw new ZeroInputException();
        }
        randFiller.fillMatrix(matrix);

        return matrix;
    }
}
