package com.company;

public class Main {

    public static void main(String[] args) {

        int [] [] mas1 = new int [3] [3];

        int [] [] mas2 = new int [3] [3];

        MatrixOperation matrix = new MatrixOperation();

        matrix.fillMatrix(mas1);

        matrix.fillMatrix(mas2);

        matrix.multiplyMatrix(mas1, mas2);

    }
}
