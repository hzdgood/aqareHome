package com.aqara.common.excel;

import com.aqara.common.entity.Collent;
import com.aqara.common.service.CollentService;
import com.aqara.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class CollentExcel {
    public static void CollentExcel(File file, CollentService CollentService) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Collent Collent = new Collent();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            Collent.setItemId(value);
                        } else if (j == 1) {
                            Collent.setProjectId(value);
                        } else if (j == 2) {
                            Collent.setCollectionStatus(value);
                        } else if (j == 3) {
                            Collent.setRefundType(value);
                        } else if (j == 4) {
                            Collent.setPaymentMethod(value);
                        } else if (j == 5) {
                            Collent.setQuotationID(value);
                        } else if (j == 6) {
                            Collent.setCollection(value);
                        } else if (j == 7) {
                            Collent.setDepartment(value);
                        } else if (j == 8) {
                            Collent.setPayee(value);
                        }
                    }
                }
                CollentService.insert(Collent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
