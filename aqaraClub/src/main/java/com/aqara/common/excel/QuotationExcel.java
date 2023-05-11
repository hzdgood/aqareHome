package com.aqara.common.excel;

import com.aqara.common.entity.Quotation;
import com.aqara.common.service.QuotationService;
import com.aqara.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class QuotationExcel {
    public static void QuotationExcel(File file, QuotationService quotationService) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Quotation Quotation = new Quotation();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            Quotation.setItemId(value);
                        } else if (j == 1) {
                            Quotation.setType(value);
                        } else if (j == 2) {
                            Quotation.setQuotationId(value); //报价单
                        } else if (j == 4) {
                            Quotation.setSales(value);//销售
                        } else if (j == 5) {
                            Quotation.setSchemeAmount(value);
                        } else if (j == 6) {
                            Quotation.setDiscountAmount(value);
                        } else if (j == 7) {
                            Quotation.setReceivedAmount(value);
                        } else if (j == 8) {
                            Quotation.setOutstandingAmount(value);
                        } else if (j == 9) {
                            Quotation.setRemark(value);
                        } else if (j == 10) {
                            Quotation.setDepartment(value);// 部门
                        }
                    }
                }
                quotationService.insert(Quotation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
