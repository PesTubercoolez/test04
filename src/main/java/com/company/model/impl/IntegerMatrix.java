package com.company.model.impl;

import com.company.model.Matrix;

public class IntegerMatrix implements Matrix {

    private int rows;
    private int columns;
    private int[][] arr;
    private int size;
    private Number value;

    public IntegerMatrix(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        this.arr = new int[rows][columns];
    }

    public IntegerMatrix() {

        this.rows = 3;
        this.columns = 3;
        this.arr = new int[rows][columns];
    }

    public IntegerMatrix(int[][] arr) {

        this.columns = arr[0].length;
        this.rows = arr.length;
        this.arr = new int[rows][columns];
        setAllValues(arr);
    }

    @Override
    public void showMatrix() {

        for (int x = 0; x < this.arr.length; x++) {

            for (int j = 0; j < this.arr[0].length; j++) {
                System.out.print(this.arr[x][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    public void setAllValues(int[][] arr) {

        if (arr.length == this.arr.length || arr[0].length == this.arr[0].length) {

            for (int x = 0; x < this.arr.length; x++) {

                for (int k = 0; k < this.arr[0].length; k++) {
                    int j = arr[x][k];
                    this.arr[x][k] = j;
                }
            }
        }
    }

    @Override
    public int getSize() {

        size = this.arr.length + this.arr[0].length;
        return size;
    }

    @Override
    public int getRows() {

        rows = this.arr.length;
        return rows;
    }

    @Override
    public int getColumns() {

        columns = this.arr[0].length;
        return columns;
    }

    public int[][] getMatrix() {

        return this.arr;
    }

    @Override
    public Number getValue(int row, int column) {

        value = this.arr[row][column];
        return value.intValue();
    }

    @Override
    public void setValue(int row, int column, Number value) {

      this.arr[row][column] = value.intValue();
    }
}


//arr[0].length - columns

//arr.length - rows