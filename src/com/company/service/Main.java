package com.company.service;

import com.company.exception.NullInputException;
import com.company.factory.CustomIntegerMatrixCreator;
import com.company.model.IntegerMatrix;
import com.company.factory.MatrixOperation;
import com.company.factory.RandomIntegerMatrixCreator;

public class Main {

    public static void main(String[] args) {

        try{

        MatrixOperation matrixMultiplier = new MatrixOperation();

        IntegerMatrix matrix1 = new CustomIntegerMatrixCreator().createMatrix();

        matrix1.showMatrix();

        IntegerMatrix matrix2 = new RandomIntegerMatrixCreator().createMatrix();

        matrix2.showMatrix();

        matrixMultiplier.multiplyMatrix(matrix1, matrix2).showMatrix();

        }catch (NullInputException e){e.printStackTrace();}
    }
}
