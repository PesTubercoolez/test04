package com.vector.model;

public interface Matrix {

     void showMatrix();
     int getSize();
     int getRows();
     int getColumns();
     Number getValue(int row, int column);
     void setValue(int row, int column, Number value);
}
