package com.company.factory;

import com.company.exception.NullInputException;
import com.company.model.IntegerMatrix;

import java.util.Scanner;

public class CustomIntegerMatrixCreator implements MatrixCreator {



    @Override
    public IntegerMatrix createMatrix() throws NullInputException {


        Scanner scanConsole = new Scanner(System.in);

        System.out.println("Enter row/s quantity");

           int xQuantity = Integer.parseInt(scanConsole.nextLine());

           System.out.println("Enter column/s quantity");

           int yQuantity = Integer.parseInt(scanConsole.nextLine());

           if (xQuantity == 0 || yQuantity == 0) throw new NullInputException("Your input mustn't be equal to 0");

          IntegerMatrix matrix = new IntegerMatrix(xQuantity, yQuantity);

           int[][] arr = new int[xQuantity][yQuantity];

           for (int x = 0; x < arr.length; x++) {

               for (int j = 0; j < arr[0].length; j++) {

                   System.out.println("Enter value");

                   arr[x][j] = Integer.parseInt(scanConsole.nextLine());
               }
           }

           matrix.setAllValues(arr);

           return matrix;
    }
}
