package com.company.factory.impl;

import com.company.exception.ZeroInputException;
import com.company.factory.MatrixCreator;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.MatrixFiller.impl.RandomIntegerMatrixFiller;


public class RandomIntegerMatrixCreator implements MatrixCreator {

    @Override
    public Matrix createMatrix(int rows, int columns) throws ZeroInputException {

        if (rows == 0 || columns == 0){

            throw new ZeroInputException();

        }

        return new RandomIntegerMatrixFiller().fillMatrix(new IntegerMatrix(rows, columns));

    }
}
