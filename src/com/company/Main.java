package com.company;
import com.company.FileConstants.FileExtensionConstants;
import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.service.FileHandler.impl.IntegerFileHandler;
import com.company.service.FileParser.FileParser;
import com.company.service.InputScanner;
import com.company.service.MatrixOperation.impl.IntegerMatrixOperation;
import com.company.factory.impl.RandomIntegerMatrixCreator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ZeroInputException, IOException {

        System.out.println(new FileParser(new IntegerFileHandler().createFile(FilePathConstants.WINDOWS_ABSOLUTE_FILE_PATH)));
        InputScanner scanner = new InputScanner();
        System.out.println("Enter first matrix rows and columns");
        Matrix matrix1 = new CustomIntegerMatrixCreator().createMatrix(scanner.scanInt(), scanner.scanInt());
        System.out.println("Enter second matrix rows and columns");
        Matrix matrix2 = new RandomIntegerMatrixCreator().createMatrix(scanner.scanInt(), scanner.scanInt());
        matrix1.showMatrix();
        matrix2.showMatrix();
        new IntegerMatrixOperation().multiplyMatrix(matrix1, matrix2).showMatrix();
    }
}
