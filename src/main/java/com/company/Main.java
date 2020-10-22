package com.company;

import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.MatrixCreator;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.service.FileHandler.FileHandler;
import com.company.service.FileHandler.impl.XLSXFileHandler;
import com.company.service.FileParser.FileParser;
import com.company.service.FileParser.impl.XLSXFileParser;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.IntegerMatrixOperation;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ZeroInputException, IOException {
        FileParser parser = new XLSXFileParser();
        CustomIntegerMatrixCreator creator = new CustomIntegerMatrixCreator();
        XLSXFileHandler fileHandler = new XLSXFileHandler();
        File readedFile = fileHandler.createFile(FilePathConstants.LINUX_ABSOLUTE_FILE_READ_PATH);
        File writedFile = fileHandler.createFile(FilePathConstants.LINUX_ABSOLUTE_FILE_WRITE_PATH);
        Matrix firstMatrix = creator.createMatrixFromFile(parser.readFile(readedFile, 1));
        Matrix secondMatrix = creator.createMatrixFromFile(parser.readFile(readedFile, 0));
        readFile(parser, firstMatrix, readedFile, 1);
        readFile(parser, secondMatrix, readedFile, 0);
        Matrix resultMatrix = new IntegerMatrixOperation().multiplyMatrix(firstMatrix, secondMatrix);
        writeFile(fileHandler, parser, writedFile, resultMatrix);
    }

    private static void writeFile(FileHandler handler, FileParser parser, File file, Matrix matrix) throws IOException{
        handler.writeFile(file, parser.parseVariablesToFile(matrix));
    }

    private static Matrix readFile(FileParser parser, Matrix matrix, File file, int numberOfSheet) throws IOException {

        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();
        filler.fillMatrixFromFile(matrix, parser.readFile(file, numberOfSheet));

        return matrix;
    }
}
