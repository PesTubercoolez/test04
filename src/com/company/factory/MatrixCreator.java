package com.company.factory;

import com.company.exception.ZeroInputException;
import com.company.model.Matrix;

public interface MatrixCreator {

    Matrix createMatrix(int rows, int columns) throws ZeroInputException;
}
