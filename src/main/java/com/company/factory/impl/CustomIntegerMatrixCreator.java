package com.company.factory.impl;

import com.company.exception.ZeroInputException;
import com.company.factory.MatrixCreator;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;

import java.time.zone.ZoneRulesException;
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

        if (matrix.getRows() == 0 || matrix.getColumns() == 0 ){
            throw new ZeroInputException();
        }

        return matrix;
    }
}