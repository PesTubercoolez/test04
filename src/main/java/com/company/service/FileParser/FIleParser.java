package com.company.service.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface FIleParser {
   ArrayList<ArrayList<String>> parseVariablesFromFile(File file, int numberOfFile) throws IOException;
}
