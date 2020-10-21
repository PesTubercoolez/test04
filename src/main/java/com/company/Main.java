package com.company;

import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.service.FileHandler.impl.IntegerFileHandler;
import com.company.service.FileParser.impl.XLSXFileParser;
import com.company.service.MatrixOperation.impl.IntegerMatrixOperation;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ZeroInputException, IOException {

        File file;
        file = new IntegerFileHandler().createFile(FilePathConstants.WINDOWS_ABSOLUTE_FILE_PATH);
        XLSXFileParser fileParser = new XLSXFileParser();
        CustomIntegerMatrixCreator creator = new CustomIntegerMatrixCreator();
        Matrix firstMatrix = creator.createMatrixFromFile(fileParser.parseVariablesFromFile(file, 1));
        Matrix secondMatrix =  creator.createMatrixFromFile(fileParser.parseVariablesFromFile(file, 0));
        secondMatrix.showMatrix();
        firstMatrix.showMatrix();
        new IntegerMatrixOperation().multiplyMatrix(secondMatrix, firstMatrix).showMatrix();
    }
}
