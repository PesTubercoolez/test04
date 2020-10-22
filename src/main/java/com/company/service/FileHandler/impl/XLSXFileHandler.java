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
    private static final String FILE_XLSX_EXT = ".xlsx";

    @Override
    public File createFile(String path) throws IOException {

        File file;

        if (path == null) {
            file = File.createTempFile("New", FILE_XLSX_EXT, new File(FilePathConstants.WINDOWS_ABSOLUTE_FILE_DIRECTORY_PATH));
        } else {
            file = new File(path);
        }

        return file;
    }

    public void writeFile(File file, List<List<String>> list) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowCount = 0;

        for (List<String> innerList : list) {
            Row row = sheet.createRow(rowCount++);
            createCellsWithVariables(row, innerList);
        }
        workbook.write(new FileOutputStream(file));
    }

    private void createCellsWithVariables(Row row, List<String> list) {

        Cell cell;

        for (int x = 0; x < list.size(); x++) {
            cell = row.createCell(x);
            cell.setCellValue(Double.parseDouble(list.get(x)));
        }
    }
}

