package com.vector.service.FileHandler.impl;

import com.vector.FileConstants.FileExtensionConstants;
import com.vector.model.Matrix;
import com.vector.model.impl.IntegerMatrix;
import com.vector.service.FileHandler.FileHandler;

import java.io.*;

public class IntegerFileHandler implements FileHandler {

    @Override
    public File createFile(String path) throws IOException {

        return new File(path);
    }

    @Override
    public void deleteFile(String path) throws IOException {

    }

    @Override
    public void cleanFile(String path) throws IOException {

    }
}

