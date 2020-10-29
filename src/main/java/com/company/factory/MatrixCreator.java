package com.company.factory;

import com.company.exception.ZeroInputException;
import com.company.model.Matrix;
import java.util.List;

public interface MatrixCreator {

    Matrix createMatrix(int rows, int columns) throws ZeroInputException;
    Matrix createMatrixFromFile(List<List<String>> list) throws ZeroInputException;
}
