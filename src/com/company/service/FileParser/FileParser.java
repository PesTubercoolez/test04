package com.company.service.FileParser;

import java.io.*;
import java.util.Scanner;

public class FileParser {

    public FileParser(){}

    public FileParser(File file) throws IOException {

      parseVariablesFromFile(file);
    }

    public String parseVariablesFromFile(File file) throws IOException {

        String variables = "";

        try (Reader reader = new FileReader(file)){
            
            Scanner scan = new Scanner(reader);

            while ((scan.hasNextLine())){
                variables.concat(scan.nextLine());
            }
        }

        return variables;
    }
}
