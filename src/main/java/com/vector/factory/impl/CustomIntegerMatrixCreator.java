package com.vector.factory.impl;

import com.vector.exception.ZeroInputException;
import com.vector.factory.MatrixCreator;
import com.vector.model.Matrix;
import com.vector.model.impl.IntegerMatrix;
import com.vector.service.MatrixFiller.impl.CustomIntegerMatrixFiller;

import java.util.ArrayList;

public class CustomIntegerMatrixCreator implements MatrixCreator {

    @Override
    public Matrix createMatrix(int rows, int columns) throws ZeroInputException {

        IntegerMatrix matrix = new IntegerMatrix(rows, columns);
        CustomIntegerMatrixFiller customFiller = new CustomIntegerMatrixFiller();

            if (rows == 0 || columns == 0) {
                throw new ZeroInputException();
            }
             customFiller.fillMatrix(matrix);

        return matrix;
    }

    public Matrix createMatrixFromFile(ArrayList<ArrayList<String>> list) throws ZeroInputException{

        IntegerMatrix matrix = new IntegerMatrix(list.size(), list.get(0).size());
        CustomIntegerMatrixFiller customFiller = new CustomIntegerMatrixFiller();

        if (matrix.getRows() == 0 || matrix.getColumns() == 0 ){
            throw new ZeroInputException();
        }
        customFiller.fillMatrixFromFile(matrix,list);

        return matrix;
    }
}