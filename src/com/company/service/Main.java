package com.company.service;

import com.company.factory.CustomIntegerMatrixCreator;
import com.company.model.IntegerMatrix;
import com.company.factory.MatrixOperation;
import com.company.factory.RandomIntegerMatrixCreator;

public class Main {

    public static void main(String[] args) {

        MatrixOperation matrixMultiplier = new MatrixOperation();

        IntegerMatrix matrix1 = new CustomIntegerMatrixCreator().createMatrix();

        IntegerMatrix matrix2 = new RandomIntegerMatrixCreator().createMatrix();

        matrixMultiplier.multiplyMatrix(matrix1.getMatrix(), matrix2.getMatrix());
    }
}
