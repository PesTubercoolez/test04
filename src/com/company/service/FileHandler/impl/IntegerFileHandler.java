package com.company.service.FileHandler.impl;

import com.company.FileConstants.FileExtensionConstants;
import com.company.model.Matrix;
import com.company.model.impl.IntegerMatrix;
import com.company.service.FileHandler.FileHandler;

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

