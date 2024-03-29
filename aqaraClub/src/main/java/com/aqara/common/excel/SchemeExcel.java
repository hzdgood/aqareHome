package com.aqara.common.excel;

import com.aqara.common.entity.Scheme;
import com.aqara.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class SchemeExcel {
    public static List<Scheme> schemeExcel(MultipartFile files) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            List<Scheme> list = new ArrayList<Scheme>();
            String CustomerName = "";
            String telephone = "";
            for (int i = 2; i <= lastRowNum; i++) {
                Scheme scheme = new Scheme();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        // String saleMoney = "";
                        if (j == 0) {
                            scheme.setOrderNumber(value);
                        } else if (j == 3) {
                            CustomerName = value;
                            scheme.setCustomerName(value);
                        } else if (j == 4) {
                            telephone = value;
                            scheme.setTelephone(value);
                        } else if (j == 5) {
                            scheme.setProjectName(CustomerName + telephone);
                        } else if (j == 12) {
                            scheme.setProductName(value);
                        } else if (j == 14) {
                            value = value.replaceAll("\t", ""); // 空格不判断
                            scheme.setProductCode(value);
                        } else if (j == 15) {
                            scheme.setNumber(value);
                        } else if (j == 16) {
                            scheme.setMoney(value);
                        } else if (j == 20) {
                            if (value.equals("") || value.equals("0.00")) {
                                scheme.setYesNoFee("0");
                                scheme.setServiceFee("0.00");
                            } else {
                                scheme.setYesNoFee("1");
                                scheme.setServiceFee("0.25");
                            }
                        }
                    }
                }
                list.add(scheme);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
