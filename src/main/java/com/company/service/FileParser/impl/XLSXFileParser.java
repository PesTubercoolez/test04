package com.company.service.FileParser.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import com.company.service.FileParser.FIleParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXFileParser implements FIleParser {

    static XSSFRow row;

    public XLSXFileParser(){}

    public XLSXFileParser(File file, int numberOfFile) throws IOException {

      parseVariablesFromFile(file, numberOfFile);
    }

    public ArrayList <ArrayList <String>> parseVariablesFromFile(File file, int numberOFFile) throws IOException {

        Double val;
        ArrayList <ArrayList <String>> variables = new ArrayList<>();
        FileInputStream reader = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(reader);
        XSSFSheet spreadsheet = workbook.getSheetAt(numberOFFile);
        Iterator <Row> rowIterator = spreadsheet.iterator();

        while (rowIterator.hasNext()){
            row = (XSSFRow) rowIterator.next();
            Iterator <Cell> cellIterator = row.cellIterator();
            ArrayList <String> a1 = new ArrayList<>();
            variables.add(a1);

            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                val = cell.getNumericCellValue();
                a1.add(val.toString());
                a1.trimToSize();
            }
        }
        variables.trimToSize();

        return variables;
    }
}
