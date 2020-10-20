package com.vector.service.MatrixFiller.impl;

import com.vector.model.Matrix;
import com.vector.service.InputScanner;
import com.vector.service.MatrixFiller.MatrixFiller;

import java.util.ArrayList;

public class CustomIntegerMatrixFiller implements MatrixFiller {

    @Override
    public Matrix fillMatrix(Matrix matrix) {

        InputScanner scanner = new InputScanner();
        System.out.println("Enter values of matrix");

        for (int x = 0; x < matrix.getRows(); x++) {

            for (int k = 0; k < matrix.getColumns(); k++) {
                matrix.setValue(x, k, scanner.scanInt());
            }
        }

        return matrix;
    }

    public Matrix fillMatrixFromFile(Matrix matrix, ArrayList<ArrayList<String>> list){

        int value;

        for (int x = 0; x < list.size(); x++){

            for (int j = 0;j < list.get(0).size(); j++){

                value = (int) Double.parseDouble(list.get(x).get(j));

                matrix.setValue(x, j, value);
            }
        }

        return matrix;
    }
}
