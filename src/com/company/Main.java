package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        MatrixOperation matrixMultiplier = new MatrixOperation();

        int [] [] arr1 = new CustomIntegerMatrixCreator().createMatrix();

        int [] [] arr2 = new RandomIntegerMatrixCreator().createMatrix();

        matrixMultiplier.multiplyMatrix(arr1, arr2);
    }
}
