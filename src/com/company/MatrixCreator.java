package com.company;

public abstract class MatrixCreator {

    public abstract int [] [] createMatrix();

    protected void showMatrix (int [] [] arr){

        for (int x = 0; x < arr.length; x++){

            for (int j = 0; j < arr [0].length; j++){

                System.out.print(arr [x] [j] + "\t");

            }

            System.out.println();

        }
        System.out.println("------------");
    }
}
