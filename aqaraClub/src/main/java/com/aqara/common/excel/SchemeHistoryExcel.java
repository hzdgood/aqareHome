package com.aqara.common.excel;

import com.aqara.common.entity.SchemeHistory;
import com.aqara.common.service.SchemeHistoryService;
import com.aqara.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class SchemeHistoryExcel {
    public static void SchemeHistoryExcel(File file, SchemeHistoryService schemeHistoryService) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                SchemeHistory SchemeHistory = new SchemeHistory();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            SchemeHistory.setItemId(value);
                        } else if (j == 1) {
                            SchemeHistory.setNumber(value);
                        } else if (j == 2) {
                            SchemeHistory.setSchemeId(value);
                        } else if (j == 3) {
                            SchemeHistory.setQuotationId(value);
                        } else if (j == 4) {
                            SchemeHistory.setQuoteOrNot(value);
                        }
                    }
                }
                schemeHistoryService.insert(SchemeHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
