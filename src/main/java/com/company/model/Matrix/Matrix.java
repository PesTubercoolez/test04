package com.company.model.Matrix;

public interface Matrix {

    void showMatrix();

    int getSize();

    int getRows();

    int getColumns();

    int [][] getArray();

    Number getValue(int row, int column);

    void setValue(int row, int column, Number value);

    Number[] getRowVector(int position);

    Number[] getColumnVector(int position);

    Number getVectorValue(int vectorPosition, int valuePosition, String vectorDirection);

    void setAllValues(int [][] array);

    void setRowVector(int position, Number[] vector);

    void setColumnVector(int position, Number[] vector);

    void setArrayRepresentation(String arrayRepresentation);

    void setVectorValue(int vectorPosition, int valuePosition, String typeOfVector, Number value);

    String getArrayRepresentation();

    boolean isRowFilled(int position);

    boolean isColumnFilled(int position);
}
