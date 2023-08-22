package com.yunqi.common.excel;

import com.yunqi.common.entity.Product;
import com.yunqi.common.service.ProductService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProductExcel {
    public static void productExcel(File file, ProductService ProductService) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Product Product = new Product();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            Product.setItemId(value);
                        } else if (j == 1) {
                            Product.setName(value);
                        } else if (j == 2) {
                            Product.setRealName(value);
                        } else if (j == 3) {
                            Product.setCode(value);
                        } else if (j == 4) {
                            Product.setScanCode(value);
                        } else if (j == 5) {
                            Product.setBomType(value);
                        } else if (j == 6) {
                            Product.setClassification(value);
                        } else if (j == 7) {
                            Product.setShipType(value);
                        } else if (j == 8) {
                            Product.setRepairPrice(value);
                        } else if (j == 9) {
                            Product.setPurchasePrice(value);
                        } else if (j == 10) {
                            Product.setPrice(value);
                        } else if (j == 11) {
                            Product.setBrand(value);
                        }
                    }
                }
                ProductService.insert(Product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
