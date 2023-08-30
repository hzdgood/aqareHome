package com.yunqi.common.excel;

import com.yunqi.common.entity.Commission;
import com.yunqi.common.service.CommissionService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class CommissionExcel {
    public static void commissionExcel(File file, CommissionService commissionService) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Commission Commission = new Commission();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            Commission.setCategory(value);
                        } else if (j == 1) {
                            Commission.setCost(value);
                        } else if (j == 2) {
                            Commission.setInstallRatio(value);
                        } else if (j == 3) {
                            Commission.setDebugRatio(value);
                        } else if (j == 4) {
                            Commission.setInstall(value);
                        } else if (j == 5) {
                            Commission.setDebug(value);
                        } else if (j == 6) {
                            Commission.setDisclose(value);
                        } else if (j == 7) {
                            Commission.setCheck(value);
                        } else if (j == 8) {
                            Commission.setHeadDoor(value);
                        } else if (j == 9) {
                            Commission.setHeadDisclose(value);
                        } else if (j == 10) {
                            Commission.setHeadComplete(value);
                        }
                    }
                }
                commissionService.insert(Commission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
