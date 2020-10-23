package com.company.service.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileHandler {

        File createFile(String path) throws IOException;
        void writeFile (File file, List <List<String>> list) throws IOException;

    }
