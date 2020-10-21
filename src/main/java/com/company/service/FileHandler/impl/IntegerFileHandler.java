package com.company.service.FileHandler.impl;

import com.company.service.FileHandler.FileHandler;
import java.io.*;

public class IntegerFileHandler implements FileHandler {

    @Override
    public File createFile(String path) throws IOException {

        return new File(path);
    }
}

