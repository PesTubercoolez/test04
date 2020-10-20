package com.vector.service.FileHandler;

import com.vector.model.Matrix;

import java.io.File;
import java.io.IOException;

public interface FileHandler {

        File createFile(String path) throws IOException;
        void deleteFile(String path) throws IOException;
        void cleanFile(String path) throws IOException;
    }
