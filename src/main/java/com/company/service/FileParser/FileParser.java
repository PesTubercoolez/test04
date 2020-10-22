package com.company.service.FileParser;

import com.company.model.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileParser {
   List <List<String>> readFromFile(File file, int numberOfFile) throws IOException;
   List <List<String>> parseVariablesToFile (Matrix matrix);
}
