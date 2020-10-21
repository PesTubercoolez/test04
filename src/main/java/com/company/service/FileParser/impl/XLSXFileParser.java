package com.company.service.FileParser.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.company.service.FileParser.FIleParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXFileParser implements FIleParser {

    static XSSFRow row;

    public XLSXFileParser(){}

    public XLSXFileParser(File file, int numberOfFile) throws IOException {

      parseVariablesFromFile(file, numberOfFile);
    }

    public List <List<String>> parseVariablesFromFile(File file, int numberOFFile) throws IOException {

        Double val;
        List <List <String>> ListOfVariables = new ArrayList<>();
        FileInputStream reader = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(reader);

        for (Row cells : workbook.getSheetAt(numberOFFile)) {
            row = (XSSFRow) cells;
            Iterator<Cell> cellIterator = row.cellIterator();
            List<String> innerListOfVariables = new ArrayList<>();
            ListOfVariables.add(innerListOfVariables);

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                val = cell.getNumericCellValue();
                innerListOfVariables.add(val.toString());
                ((ArrayList<String>) innerListOfVariables).trimToSize();
            }
        }
        ((ArrayList<List<String>>) ListOfVariables).trimToSize();

        return ListOfVariables;
    }
}
