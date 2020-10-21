package com.company.service.FileHandler;

import java.io.File;
import java.io.IOException;

public interface FileHandler {

        File createFile(String path) throws IOException;
    }
