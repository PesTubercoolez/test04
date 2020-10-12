package com.company;

import java.util.Scanner;

public class CustomIntegerMatrixCreator extends MatrixCreator {

    @Override
    public int [][] createMatrix() throws IllegalArgumentException {

        Scanner scanConsole = new Scanner(System.in);

        System.out.println("Enter line quantity");

        int xQuantity = Integer.parseInt(scanConsole.nextLine());

        System.out.println("Enter column quantity");

        int yQuantity = Integer.parseInt(scanConsole.nextLine());

        int[][] arr = new int[xQuantity][yQuantity];

        for (int x = 0; x < arr.length; x++) {

            for (int j = 0; j < arr[0].length; j++) {

                System.out.println("Enter value");

                System.out.println();

                arr[x][j] = Integer.parseInt(scanConsole.nextLine());

            }
        }

        super.showMatrix(arr);

        return arr;
    }
}
