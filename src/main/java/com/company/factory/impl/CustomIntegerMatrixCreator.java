package com.company.factory.impl;

import com.company.exception.ZeroInputException;
import com.company.factory.MatrixCreator;
import com.company.model.Matrix.Matrix;
import com.company.model.Matrix.impl.IntegerMatrix;
import com.company.service.InputScanner;
import com.company.service.MatrixConverterToJSON.MatrixConverterToJSON;
import com.company.service.MatrixFiller.MatrixFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomIntegerMatrixCreator implements MatrixCreator {

    @Autowired
    MatrixFiller filler;
    @Autowired
    MatrixConverterToJSON converter;
    @Autowired
    InputScanner scanner;

    public CustomIntegerMatrixCreator(MatrixFiller filler){
        this.filler=filler;
    }

    @Override
    public Matrix createMatrix(int rows, int columns) throws ZeroInputException {

        if (rows == 0 || columns == 0) {
            throw new ZeroInputException();
        }
        Matrix matrix = new IntegerMatrix(rows, columns);
        filler.fillMatrixManually(matrix);
        matrix.setArrayRepresentation(converter.convertMatrixArrayToJSON(matrix));
        return matrix;
    }

    @Override
    public Matrix createMatrixFromFile(List<List<String>> list) throws ZeroInputException {

        IntegerMatrix matrix = new IntegerMatrix(list.size(), list.get(0).size());

        if (matrix.getRows() == 0 || matrix.getColumns() == 0) {
            throw new ZeroInputException();
        }

        return matrix;
    }

    @Override
    public List<Matrix> createMatrixList(int matrixQuantity) throws ZeroInputException{
        List<Matrix> matrixList = new ArrayList<>(matrixQuantity);

        for (int x = 0; x<matrixQuantity; x++){
            matrixList.add(createMatrix(scanner.scanInt(), scanner.scanInt()));
        }

        return matrixList;
    }
}
