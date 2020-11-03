package com.company;

import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
import com.company.factory.impl.CustomIntegerMatrixCreator;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.FileHandler.FileHandler;
import com.company.service.FileHandler.impl.XLSXFileHandler;
import com.company.service.FileParser.FileParser;
import com.company.service.FileParser.impl.XLSXFileParser;
import com.company.service.MatrixFiller.impl.CustomIntegerMatrixFiller;
import com.company.service.MatrixOperation.impl.VectorIntegerMatrixMultiplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {

        String readPath = FilePathConstants.LINUX_ABSOLUTE_FILE_READ_PATH;
        FileParser parser = new XLSXFileParser();
        XLSXFileHandler fileHandler = new XLSXFileHandler();
        Matrix firstMatrix = readFile(parser, readPath, 0, fileHandler);
        Matrix secondMatrix = readFile(parser, readPath, 1, fileHandler);
        Matrix thirdMatrix =  enhancedThreadMultiplyer(firstMatrix, secondMatrix);
        writeFile(fileHandler, parser,FilePathConstants.LINUX_ABSOLUTE_FILE_WRITE_PATH,thirdMatrix);
    }

    private static Matrix enhancedThreadMultiplyer(Matrix firstMatrix, Matrix secondMatrix) throws Exception {

        Matrix thirdMatrix = new IntegerMatrix(firstMatrix.getRows(), secondMatrix.getColumns());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Number[]> futureVector;
        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();

        for (int x = 0; x < firstMatrix.getRows() ; x++) {
            futureVector = executor.submit(new VectorIntegerMatrixMultiplication(firstMatrix, secondMatrix, x));
            filler.fillMatrixFromVector(thirdMatrix, futureVector.get(), x);
        }
        executor.shutdown();
        thirdMatrix.showMatrix();
        return thirdMatrix;
    }

    private static void writeFile(FileHandler handler, FileParser parser, String path, Matrix resultMatrix) throws IOException {

        File file = handler.createFile(path);
        handler.writeFile(file, parser.parseVariablesToList(resultMatrix));
    }

    private static Matrix readFile(FileParser parser, String path, int numberOfSheet, FileHandler handler) throws IOException, ZeroInputException {

        File file = handler.createFile(path);
        CustomIntegerMatrixCreator creator = new CustomIntegerMatrixCreator();
        Matrix matrix = creator.createMatrixFromFile(parser.readFromFile(file, numberOfSheet));
        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();
        filler.fillMatrixFromList(matrix, parser.readFromFile(file, numberOfSheet));

        return matrix;
    }
}
