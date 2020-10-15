package com.company.service.FileHandler;

import com.company.model.Matrix;

import java.io.File;
import java.io.IOException;

public interface FileHandler {

        File createFile(String path) throws IOException;
        void deleteFile(String path) throws IOException;
    }
