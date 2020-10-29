package com.company;

import com.company.FileConstants.FilePathConstants;
import com.company.exception.ZeroInputException;
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

public class Main extends Thread {

    public static void main(String[] args) throws ZeroInputException, IOException {

        new Main().start();
    }

    @Override
    public void run() {

        String readPath = FilePathConstants.LINUX_ABSOLUTE_FILE_READ_PATH;
        FileParser parser = new XLSXFileParser();
        XLSXFileHandler fileHandler = new XLSXFileHandler();

        try {
            Matrix firstMatrix = readFile(parser, readPath, 0, fileHandler);
            Matrix secondMatrix = readFile(parser, readPath, 1, fileHandler);
            writeFile(fileHandler, parser, FilePathConstants.LINUX_ABSOLUTE_FILE_WRITE_PATH, firstMatrix, secondMatrix);
        } catch (IOException | ZeroInputException e) {
            e.printStackTrace();
        }
        super.run();
    }

    private static void writeFile(FileHandler handler, FileParser parser, String path, Matrix firstMatrix, Matrix secondMatrix) throws IOException {

        File file = handler.createFile(path);
        Matrix thirdMatrix = new IntegerMatrixOperation().multiplyMatrix(firstMatrix, secondMatrix);
        handler.writeFile(file, parser.parseVariablesToList(thirdMatrix));
    }

    private static Matrix readFile(FileParser parser, String path, int numberOfSheet, FileHandler handler) throws IOException, ZeroInputException {

        File file = handler.createFile(path);
        CustomIntegerMatrixCreator creator = new CustomIntegerMatrixCreator();
        Matrix matrix = creator.createMatrixFromFile(parser.readFromFile(file, numberOfSheet));
        CustomIntegerMatrixFiller filler = new CustomIntegerMatrixFiller();
        filler.fillMatrixFromFile(matrix, parser.readFromFile(file, numberOfSheet));

        return matrix;
    }
}
