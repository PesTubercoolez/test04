package com.company.service.FileHandler.impl;

import com.company.constants.FileConstants.FilePathConstants;
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
            file = File.createTempFile("New", FILE_XLSX_EXT, new File(FilePathConstants.LINUX_ABSOLUTE_FILE_DIRECTORY_PATH));
        } else {
            file = new File(path);
        }

        return file;
    }

    public void writeFile(File file, List<List<String>> list) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowCount = 0;
        Cell cell;

        for (List<String> innerList : list) {
            Row row = sheet.createRow(rowCount++);
            for (int x = 0; x < list.get(0).size(); x++) {
                cell = row.createCell(x);
                cell.setCellValue(Double.parseDouble(innerList.get(x)));
            }
        }
        workbook.write(new FileOutputStream(file));
    }
}
