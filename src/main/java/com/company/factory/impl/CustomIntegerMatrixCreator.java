package com.company.factory.impl;

import com.company.exception.ZeroInputException;
import com.company.factory.MatrixCreator;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.FileParser.FileParser;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomIntegerMatrixCreator implements MatrixCreator {

    @Override
    public Matrix createMatrix(int rows, int columns) throws ZeroInputException {

        if (rows == 0 || columns == 0) {
            throw new ZeroInputException();
        }
        return new IntegerMatrix(rows, columns);
    }

    public Matrix createMatrixFromFile(FileParser parser, File file, int numberOfSheet) throws ZeroInputException, IOException {

        List <List <String>> matrixList = parser.readFile(file, numberOfSheet);
        IntegerMatrix matrix = new IntegerMatrix(matrixList.size(), matrixList.get(0).size());

        if (matrix.getRows() == 0 || matrix.getColumns() == 0 ){
            throw new ZeroInputException();
        }
        new CustomIntegerMatrixFiller().fillMatrixFromFile(matrix, matrixList);

        return matrix;
    }
}