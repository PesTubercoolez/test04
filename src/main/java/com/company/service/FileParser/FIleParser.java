package com.company.service.FileParser;

import com.company.model.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FIleParser {
   List <List<String>> parseVariablesFromFile(File file, int numberOfFile) throws IOException;
   List <List<String>> parseVariablesToFile (Matrix matrix);
}
