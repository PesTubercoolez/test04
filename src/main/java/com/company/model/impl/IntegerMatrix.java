package com.company.model.impl;

import com.company.model.Matrix;

public class IntegerMatrix implements Matrix {

    private int rows;
    private int columns;
    private int[][] arr;
    private int size;
    private Number value;
    private Number[] vector;
    private boolean hasContent = false;

    public IntegerMatrix(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
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

        if (arr.length == this.arr.length && arr[0].length == this.arr[0].length) {

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

        size = this.arr.length * this.arr[0].length;
        return size;
    }

    @Override
    public int getRows() {

        this.rows = this.arr.length;
        return this.rows;
    }

    @Override
    public int getColumns() {

        this.columns = this.arr[0].length;
        return this.columns;
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

    @Override
    public Number[] getRowVector(int position) {

        this.vector = new Number[this.arr.length];

        for (int x = 0; x < this.arr.length; x++) {
            this.vector[x] = this.arr[x][position];
        }

        return this.vector;
    }

    @Override
    public Number[] getColumnVector(int position) {

        this.vector = new Number[this.arr[0].length];

        for (int x = 0; x < this.arr[0].length; x++) {
            this.vector[x] = this.arr[position][x];
        }

        return this.vector;
    }

    @Override
    public Number getVectorValue(int vectorPosition, int valuePosition, String vectorDirection) {

        switch (vectorDirection) {

            case "column":
                this.vector = getRowVector(vectorPosition);
                this.value = this.vector[valuePosition];
                break;

            case "row":
                this.vector = getColumnVector(vectorPosition);
                this.value = this.vector[valuePosition];
                break;
        }

        return this.value;
    }

    public void setRowVector(int position, Number[] vector) {

        for (int x = 0; x < this.arr.length; x++) {
            this.arr[x][position] = vector[x].intValue();
        }
    }

    public void setColumnVector(int position, Number[] vector) {

        for (int x = 0; x < this.arr[0].length; x++) {
            this.arr[position][x] = vector[x].intValue();
        }
    }

    public void setVectorValue(int vectorPosition, int valuePosition, String vectorDirection, Number value) {

        switch (vectorDirection) {

            case "row":
                this.vector = getRowVector(vectorPosition);
                this.vector[valuePosition] = value;
                break;

            case "column":
                this.vector = getColumnVector(vectorPosition);
                this.vector[valuePosition] = value;
                break;
        }
    }

    public boolean isRowFilled(int position) {

        this.vector = getRowVector(position);

        for (int x = 0; x < this.vector.length; x++) {

            if (this.vector[x].intValue() != 0) {
                this.hasContent = true;

            } else {
                this.hasContent = false;
                break;
            }
        }

        return this.hasContent;
    }

    public boolean isColumnFilled(int position) {

        this.vector = getColumnVector(position);

        for (int l = 0; l < this.vector.length; l++) {

            if (this.vector[l].intValue() != 0) {
                this.hasContent = true;

            } else {
                this.hasContent = false;
                break;
            }
        }

        return this.hasContent;
    }
}
