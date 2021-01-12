package com.company.model.Matrix.impl;

import com.company.model.Matrix.Matrix;
import com.company.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "matrix_repository")
public class IntegerMatrix implements Matrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matrix_id")
    private Long id;
    @Column(name = "rows")
    private int rows;
    @Column(name = "columns")
    private int columns;
    @Column(name = "matrix_values")
    private String arrayRepresentation;
    @Transient
    private int size;
    @Transient
    private int[][] array;
    @Transient
    private Number value;
    @Transient
    private Number[] vector;
    @Transient
    private boolean hasContent = false;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public IntegerMatrix(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        this.array = new int[rows][columns];
    }

    public IntegerMatrix(int[][] array) {

        this.columns = array[0].length;
        this.rows = array.length;
        this.array = new int[rows][columns];
        setAllValues(array);
    }

    public IntegerMatrix(){

    }

    public IntegerMatrix(Matrix huj) {
        this.columns = huj.getColumns();
        this.rows = huj.getRows();
        this.array = huj.getArray();
    }

    @Override
    public void showMatrix() {

        for (int x = 0; x < this.array.length; x++) {

            for (int j = 0; j < this.array[0].length; j++) {
                System.out.print(this.array[x][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    @Override
    public void setAllValues(int[][] arr) {
        this.array = new int[arr.length][arr[0].length];
            for (int x = 0; x < this.array.length; x++) {

                for (int k = 0; k < this.array[0].length; k++) {
                    int j = arr[x][k];
                    this.array[x][k] = j;
                }
            }
    }

    @Override
    public int getSize() {

        size = this.array.length * this.array[0].length;
        return size;
    }

    @Override
    public int getRows() {

        this.rows = this.array.length;
        return this.rows;
    }

    @Override
    public int getColumns() {

        this.columns = this.array[0].length;
        return this.columns;
    }

    @Override
    public int[][] getArray() {

        return this.array;
    }

    @Override
    public Number getValue(int row, int column) {

        value = this.array[row][column];
        return value.intValue();
    }

    @Override
    public void setValue(int row, int column, Number value) {

        this.array[row][column] = value.intValue();
    }

    @Override
    public Number[] getRowVector(int position) {

        this.vector = new Number[this.array.length];

        for (int x = 0; x < this.array.length; x++) {
            this.vector[x] = this.array[x][position];
        }

        return this.vector;
    }

    @Override
    public Number[] getColumnVector(int position) {

        this.vector = new Number[this.array[0].length];

        for (int x = 0; x < this.array[0].length; x++) {
            this.vector[x] = this.array[position][x];
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

    @Override
    public void setRowVector(int position, Number[] vector) {

        for (int x = 0; x < this.array.length; x++) {
            this.array[x][position] = vector[x].intValue();
        }
    }

    @Override
    public void setColumnVector(int position, Number[] vector) {

        for (int x = 0; x < this.array[0].length; x++) {
            this.array[position][x] = vector[x].intValue();
        }
    }

    @Override
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

    @Override
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

    @Override
    public String getArrayRepresentation(){
        return this.arrayRepresentation;
    }

    @Override
    public void setArrayRepresentation(String arrayRepresentation){
        this.arrayRepresentation = arrayRepresentation;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}
