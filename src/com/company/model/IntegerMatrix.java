package com.company.model;

public class IntegerMatrix  {

    private int rows;

    private int columns;

    private int [] [] arr;

    public IntegerMatrix(int rows, int columns){

        this.rows =rows;

        this.columns = columns;

        arr = new int [this.rows] [this.columns];

    }

    IntegerMatrix (){

        this.rows = 3;

        this.columns = 3;

        arr = new int [this.rows] [this.columns];
    }

    public IntegerMatrix(int[][] arr){

        this.arr = arr;
    }

    public void showMatrix(){

        for (int x = 0; x < this.arr.length; x++){

            for (int j = 0; j < this.arr [0].length; j++){

                System.out.print(this.arr [x] [j] + "\t");

            }

            System.out.println();

        }
        System.out.println("------------");
    }

    public void setValue(int [] [] arr){

        for (int x = 0; x < this.arr.length; x++){

            for (int k = 0; k < this.arr [0].length; k++){

                this.arr [x] [k] = arr [x] [k];
            }
        }
    }

    public int getLength(){

        int length = this.arr.length;

        return length;
    }

    public int getRows(){

        int rows = this.rows;

        return rows;
    }

    public int getColumns (){

        int columns = this.columns;

        return columns;
    }

    public int [] [] getMatrix (){

        return this.arr;
    }
}
