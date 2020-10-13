package com.company.factory;

import com.company.exception.NullInputException;
import com.company.model.IntegerMatrix;

public interface MatrixCreator {

    public abstract IntegerMatrix createMatrix() throws NullInputException;

}
