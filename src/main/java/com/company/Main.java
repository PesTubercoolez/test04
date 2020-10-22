package com.company;

import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.service.FileHandler.impl.XLSXFileHandler;
import com.company.service.FileParser.FileParser;
import com.company.service.FileParser.impl.XLSXFileParser;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.IntegerMatrixOperation;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ZeroInputException, IOException {
        FileParser parser = new XLSXFileParser();
        CustomIntegerMatrixCreator creator = new CustomIntegerMatrixCreator();
        XLSXFileHandler fileHandler = new XLSXFileHandler();

        File readFile = fileHandler.createFile(FilePathConstants.WINDOWS_ABSOLUTE_FILE_READ_PATH);
        File writeFile = fileHandler.createFile(FilePathConstants.WINDOWS_ABSOLUTE_FILE_WRITE_PATH);

        Matrix firstMatrix = creator.createMatrixFromFile(parser, readFile, 1);
        Matrix secondMatrix = creator.createMatrixFromFile(parser, readFile, 0);

        Matrix resultMatrix = new IntegerMatrixOperation().multiplyMatrix(firstMatrix, secondMatrix);
        resultMatrix.showMatrix();

        fileHandler.writeFile(writeFile, parser.parseVariablesToFile(resultMatrix));
    }
}
