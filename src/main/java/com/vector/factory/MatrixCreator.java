package com.vector.factory;

import com.vector.exception.ZeroInputException;
import com.vector.model.Matrix;

public interface MatrixCreator {

    Matrix createMatrix(int rows, int columns) throws ZeroInputException;
}
