package com.yunqi.common.excel;

import com.yunqi.common.entity.Scheme;
import com.yunqi.common.service.SchemeService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SchemeExcel {
    public static List<Scheme> schemeExcel(MultipartFile files) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            List<Scheme> list = new ArrayList<Scheme>();
            for (int i = 2; i <= lastRowNum; i++) {
                Scheme scheme = new Scheme();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 15) {
                            scheme.setNumber(value);
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
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
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
                        if (j == 1) {
                            Scheme.setProjectId(value); //项目
                        } else if (j == 2) {
                            Scheme.setProductId(value);//产品
                        }
                        if (j == 5) {
                            Scheme.setNumber(value);
                        }
                    }
                }
                schemeService.insert(Scheme);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
