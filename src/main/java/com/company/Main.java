package com.company;

import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.service.FileHandler.impl.XLSXFileHandler;
import com.company.service.FileParser.impl.XLSXFileParser;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.IntegerMatrixOperation;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ZeroInputException, IOException {

        File readFile;
        File writeFile;
        XLSXFileHandler fileHandler = new XLSXFileHandler();
        readFile = fileHandler.createFile(FilePathConstants.WINDOWS_ABSOLUTE_FILE_READ_PATH);
        writeFile = fileHandler.createFile(FilePathConstants.WINDOWS_ABSOLUTE_FILE_WRITE_PATH);
        XLSXFileParser fileParser = new XLSXFileParser();
        CustomIntegerMatrixCreator creator = new CustomIntegerMatrixCreator();
        Matrix firstMatrix = creator.createMatrixFromFile(fileParser.parseVariablesFromFile(readFile, 1));
        Matrix secondMatrix =  creator.createMatrixFromFile(fileParser.parseVariablesFromFile(readFile, 0));
        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();
        filler.fillMatrixFromFile(firstMatrix, fileParser.parseVariablesFromFile(readFile, 1));
        filler.fillMatrixFromFile(secondMatrix, fileParser.parseVariablesFromFile(readFile, 0));
        secondMatrix.showMatrix();
        firstMatrix.showMatrix();
        Matrix resultMatrix = new IntegerMatrixOperation().multiplyMatrix(firstMatrix, secondMatrix);
        resultMatrix.showMatrix();
        fileHandler.addInfoToFile(writeFile, fileParser.parseVariablesToFile(resultMatrix));
    }
}
