package com.aqara.common.excel;

import com.aqara.common.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProductExcel {
    public static List<Product> productExcel(MultipartFile file) {
        List<Product> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Product Product = new Product();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && !cell.equals(null)) {
                        if (j == 0) {
                            Product.setItemId(cell.getStringCellValue());
                        } else if (j == 1) {
                            Product.setName(cell.getStringCellValue());
                        } else if (j == 2) {
                            Product.setRealName(cell.getStringCellValue());
                        } else if (j == 4) {
                            Product.setCode(cell.getStringCellValue());
                        } else if (j == 5) {
                            Product.setScanCode(cell.getStringCellValue());
                        } else if (j == 6) {
                            Product.setBomType(cell.getStringCellValue());
                        } else if (j == 7) {
                            Product.setClassification(cell.getStringCellValue());
                        } else if (j == 8) {
                            Product.setShipType(cell.getStringCellValue());
                        } else if (j == 9) {
                            Product.setRepairPrice(cell.getStringCellValue());
                        } else if (j == 10) {
                            Product.setPurchasePrice(cell.getStringCellValue());
                        } else if (j == 11) {
                            Product.setPrice(cell.getStringCellValue());
                        } else if (j == 12) {
                            Product.setBrand(cell.getStringCellValue());
                        }
                    }
                }
                list.add(Product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
