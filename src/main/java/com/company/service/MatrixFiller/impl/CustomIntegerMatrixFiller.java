package com.company.service.MatrixFiller.impl;

import com.company.model.Matrix;
import com.company.service.InputScanner;
import com.company.service.MatrixFiller.MatrixFiller;

public class CustomIntegerMatrixFiller implements MatrixFiller {

    @Override
    public void fillMatrixManually(Matrix matrix) {
        InputScanner scanner = new InputScanner();
        System.out.println("Enter values of matrix");

        for (int x = 0; x < matrix.getRows(); x++) {
            for (int k = 0; k < matrix.getColumns(); k++) {
                matrix.setValue(x, k, scanner.scanInt());
            }
        }
    }

    public void fillMatrixFromVector(Matrix matrix, Number[] vector, int vectorPosition) {
        matrix.setColumnVector(vectorPosition, vector);
    }
}
