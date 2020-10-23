package com.company.factory.impl;

import com.company.exception.ZeroInputException;
import com.company.factory.MatrixCreator;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import java.util.List;

public class CustomIntegerMatrixCreator implements MatrixCreator {

    @Override
    public Matrix createMatrix(int rows, int columns) throws ZeroInputException {

        if (rows == 0 || columns == 0) {
            throw new ZeroInputException();
        }
        return new IntegerMatrix(rows, columns);
    }

    public Matrix createMatrixFromFile(List <List<String>> list) throws ZeroInputException {

        IntegerMatrix matrix = new IntegerMatrix(list.size(), list.get(0).size());

        if (matrix.getRows() == 0 || matrix.getColumns() == 0 ){
            throw new ZeroInputException();
        }

        return matrix;
    }

}