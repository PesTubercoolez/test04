package com.company.model;

public class IntegerMatrix  {

    private int rows;

    private int columns;

    private int [] [] arr;

    private int length;

    private int value;

    public IntegerMatrix(int rows, int columns){

        this.rows = rows;

        this.columns = columns;

        this.arr = new int [rows] [columns];

    }

    public IntegerMatrix(){

        this.rows = 3;

        this.columns = 3;

        this.arr = new int [rows] [columns];

    }

    public IntegerMatrix(int[][] arr){

        this.columns = arr[0].length;

        this.rows = arr.length;

        this.arr = new int [rows] [columns];

        setAllValues(arr);
    }

    public void showMatrix(){

        for (int x = 0; x < this.arr.length; x++){

            for (int j = 0; j < this.arr [0].length; j++){

                System.out.print(this.arr [x] [j] + "\t");

            }

            System.out.println();

        }
        System.out.println("----------");

    }

    public void setAllValues(int [] [] arr){

        if (arr.length == this.arr.length || arr[0].length == this.arr[0].length) {

            for (int x = 0; x < this.arr.length; x++) {

                for (int k = 0; k < this.arr[0].length; k++) {

                    int j = arr[x][k];

                    this.arr[x][k] = j;
                }
            }
        }
    }

    public int getLength(){

        length = this.arr.length;

        return length;
    }

    public int getRows(){

        rows = this.arr.length;

        return rows;
    }

    public int getColumns(){

        columns = this.arr[0].length;

        return columns;
    }

    public int [] [] getMatrix (){

        return this.arr;
    }

    public int getValue(int row, int column){

        value = this.arr [row] [column];

        return value;
    }

    public void setValue(int row, int column, int value){

         this.arr [row] [column] = value;
    }
}


//arr[0].length - columns

//arr.length - rows