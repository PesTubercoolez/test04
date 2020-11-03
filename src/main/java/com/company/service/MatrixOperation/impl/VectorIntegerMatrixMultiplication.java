package com.company.service.MatrixOperation.impl;

import com.company.model.Matrix;
import java.util.concurrent.Callable;

public class VectorIntegerMatrixMultiplication extends IntegerMatrixOperation implements Callable<Number[]> {

    Matrix firstMatrix;
    Matrix secondMatrix;
    int rowPosition;

    public VectorIntegerMatrixMultiplication(Matrix firstMatrix, Matrix secondMatrix, int position ) throws Exception {

        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.rowPosition = position;
    }

    @Override
    public Number [] call() throws Exception {

        return super.multiplyMatrixWithVectors(firstMatrix, secondMatrix, rowPosition);
    }
}
