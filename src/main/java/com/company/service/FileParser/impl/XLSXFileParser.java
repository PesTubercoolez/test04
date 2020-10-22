package com.company.service.FileParser.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.company.model.Matrix;
import com.company.service.FileParser.FileParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXFileParser implements FileParser {

    static XSSFRow row;

    public XLSXFileParser(){}

    public XLSXFileParser(File file, int numberOfFile) throws IOException {

      readFile(file, numberOfFile);
    }

    public List <List<String>> readFile(File file, int numberOFFile) throws IOException {

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

    @Override
    public List<List<String>> parseVariablesToFile(Matrix matrix)  {

        List <List<String>> importList = new ArrayList<>(matrix.getRows());
        String importInformation;

        for (int x = 0; x < matrix.getRows();x++) {
            List <String> innerList = new ArrayList<>(matrix.getColumns());
            importList.add(innerList);

            for (int j = 0; j < matrix.getColumns(); j++) {
                importInformation = matrix.getValue(x, j).toString();
                innerList.add(importInformation);
            }
        }

        return importList;
    }
}
