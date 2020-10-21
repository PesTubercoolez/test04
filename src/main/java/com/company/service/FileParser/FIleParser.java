package com.company.service.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FIleParser {
   List <List<String>> parseVariablesFromFile(File file, int numberOfFile) throws IOException;
}
