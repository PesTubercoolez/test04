package com.company.service.FileHandler.impl;

import com.company.FileConstants.FilePathConstants;
import com.company.service.FileHandler.FileHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.List;

public class XLSXFileHandler implements FileHandler {

    @Override
    public File createFile(String path) throws IOException {

        File file;

        if (path == null){
            file = File.createTempFile("New", ".xlsx", new File(FilePathConstants.WINDOWS_ABSOLUTE_FILE_DIRECTORY_PATH));
        }else {
            file = new File(path);
        }

        return file;
    }

    public void addInfoToFile (File file, List <List<String>> list) throws IOException{

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowCount = 0;

        for (List <String> list1: list){
            Row row = sheet.createRow(rowCount++);
            createCellsWithVariables(row, list1);
        }
        workbook.write(new FileOutputStream(file));
    }

    private void createCellsWithVariables (Row row, List<String> list){

        Cell cell;

        for (int x = 0; x < list.size();x++) {
            cell = row.createCell(x);
            cell.setCellValue(Double.parseDouble(list.get(x)));
        }
    }
}

