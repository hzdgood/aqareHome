package com.yunqi.common.excel;

import com.yunqi.common.entity.Commission;
import com.yunqi.common.service.CommissionService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

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
                        if (j == 0) {
                            Commission.setCategory(cell.getStringCellValue());
                        } else if (j == 1) {
                            Commission.setCost(cell.getNumericCellValue());
                        } else if (j == 2) {
                            Commission.setInstallRatio(cell.getNumericCellValue());
                        } else if (j == 3) {
                            Commission.setDebugRatio(cell.getNumericCellValue());
                        } else if (j == 4) {
                            Commission.setInstall(cell.getNumericCellValue());
                        } else if (j == 5) {
                            Commission.setDebug(cell.getNumericCellValue());
                        } else if (j == 6) {
                            Commission.setDisclose(cell.getNumericCellValue());
                        } else if (j == 7) {
                            Commission.setCheck(cell.getNumericCellValue());
                        } else if (j == 8) {
                            Commission.setHead(cell.getNumericCellValue());
                        } else if (j == 9) {
                            Commission.setHeadDoor(cell.getNumericCellValue());
                        } else if (j == 10) {
                            Commission.setHeadDisclose(cell.getNumericCellValue());
                        } else if (j == 11) {
                            Commission.setInstallContribute(cell.getNumericCellValue());
                        } else if (j == 12) {
                            Commission.setDebugContribute(cell.getNumericCellValue());
                        } else if (j == 13) {
                            Commission.setItemId(cell.getStringCellValue());
                        }
                    }
                }
                Commission.setCreateName("aqara");
                Commission.setCreateTime(new Date());
                commissionService.insert(Commission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
