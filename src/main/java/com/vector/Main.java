package com.vector;

import com.vector.FileConstants.FilePathConstants;
import com.vector.exception.ZeroInputException;
import com.vector.factory.impl.CustomIntegerMatrixCreator;
import com.vector.factory.impl.RandomIntegerMatrixCreator;
import com.vector.model.Matrix;
import com.vector.service.FileHandler.impl.IntegerFileHandler;
import com.vector.service.FileParser.impl.XLSXFileParser;
import com.vector.service.InputScanner;
import com.vector.service.MatrixOperation.impl.IntegerMatrixOperation;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ZeroInputException, IOException {


        File file;
        IntegerFileHandler fileHandler = new IntegerFileHandler();
        file = fileHandler.createFile(FilePathConstants.WINDOWS_ABSOLUTE_FILE_PATH);
        XLSXFileParser fileParser = new XLSXFileParser();
        Matrix matrix3 = new CustomIntegerMatrixCreator().createMatrixFromFile(fileParser.parseVariablesFromFile(file, 0));
        matrix3.showMatrix();
        Matrix matrix4 = new CustomIntegerMatrixCreator().createMatrixFromFile(fileParser.parseVariablesFromFile(file, 1));
        matrix4.showMatrix();
        new IntegerMatrixOperation().multiplyMatrix(matrix3, matrix4).showMatrix();
       /* InputScanner scanner = new InputScanner();
        System.out.println("Enter first matrix rows and columns");
        Matrix matrix1 = new CustomIntegerMatrixCreator().createMatrix(scanner.scanInt(), scanner.scanInt());
        matrix1.showMatrix();
        matrix3.showMatrix();
        new IntegerMatrixOperation().multiplyMatrix(matrix1, matrix3).showMatrix();*/
    }
}
