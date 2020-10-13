package com.company.factory;

import com.company.model.IntegerMatrix;

import java.util.Scanner;

public class CustomIntegerMatrixCreator extends MatrixCreator {


     IntegerMatrix matrix;

    @Override
    public IntegerMatrix createMatrix() throws IllegalArgumentException {

        Scanner scanConsole = new Scanner(System.in);

        System.out.println("Enter line quantity");

        int xQuantity = Integer.parseInt(scanConsole.nextLine());

        System.out.println("Enter column quantity");

        int yQuantity = Integer.parseInt(scanConsole.nextLine());

        if (xQuantity == 0 || yQuantity ==0){

            System.out.println("The quantity of columns/rows should be more than 0");

            System.exit(0);

        }

        int arr [] [] = new int [xQuantity] [yQuantity];

        for (int x = 0; x < arr.length; x++) {

            for (int j = 0; j < arr [0].length; j++) {

                System.out.println("Enter value");

                System.out.println();

                arr[x][j] = Integer.parseInt(scanConsole.nextLine());
            }
        }

        matrix = new IntegerMatrix(arr);

        matrix.showMatrix();

        return matrix;
    }
}
