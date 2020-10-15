package com.company.service.FileHandler.impl;

import com.company.FileConstants.FileExtensionConstants;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.FileHandler.FileHandler;

import java.io.*;

public class IntegerFileHandler implements FileHandler {

    IntegerFileHandler(int extension, File file) throws IOException {

    switch (extension){

        case FileExtensionConstants.TXT_EXTENSION:

            parseVariablesFromFile(file);
            break;

        case FileExtensionConstants.DOC_EXTENSION:
            createMatrixFromVariables(parseVariablesFromFile(file));
            break;
    }
    }

    IntegerFileHandler(){}

    @Override
    public File createFile(String path) throws IOException {

        File file;
        file = File.createTempFile("New File", ".txt", new File(path));

        return file;
    }

    @Override
    public Matrix createMatrixFromVariables(String variables) {

        Matrix matrix;
        matrix = new IntegerMatrix();

        return matrix;
    }

    public String parseVariablesFromFile(File file) throws IOException{

        String variables;

        try (Reader reader = new BufferedReader(new FileReader(file))){

        int vars = reader.read();
        variables = Integer.toString(vars);
        }

        return variables;
    }
}
