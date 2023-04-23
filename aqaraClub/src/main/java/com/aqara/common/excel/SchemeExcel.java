package com.aqara.common.excel;

import com.aqara.common.entity.Scheme;
import com.aqara.common.service.SchemeService;
import com.aqara.common.utils.ExcelUtil;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;

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
                                scheme.setServiceFee("0.15");
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

    public static void schemeToExcel(File file, SchemeService schemeService) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                    .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                    .open(fileInputStream);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Scheme Scheme = new Scheme();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            Scheme.setItemId(value);
                        } else if (j == 1) {
                            Scheme.setProjectId(value);
                        } else if (j == 2) {
                            Scheme.setProductId(value);
                        } else if (j == 3) {
                            Scheme.setContractQuantity(value);
                        } else if (j == 4) {
                            Scheme.setNumber(value);
                        } else if (j == 5) {
                            Scheme.setMoney(value);
                        } else if (j == 6) {
                            Scheme.setYesNoFee(value);
                        } else if (j == 7) {
                            Scheme.setServiceFee(value);
                        }
                    }
                }
                schemeService.insertToExcel(Scheme);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
