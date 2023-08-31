package com.yunqi.common.excel;

import com.yunqi.common.entity.Product;
import com.yunqi.common.entity.Project;
import com.yunqi.common.entity.Scheme;
import com.yunqi.common.service.SchemeService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class SchemeExcel {
    public static void schemeToExcel(File file, SchemeService schemeService, List<Project> project, List<Product> product) {
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
                            for (Project p : project) {
                                if (p.getTelephone().equals(value)) {
                                    Scheme.setProjectId(p.getId());
                                }
                            }
                        } else if (j == 2) {
                            for (Product p : product) {
                                if (p.getCode().equals(value)) {
                                    Scheme.setProductId(p.getId());
                                }
                            }
                        } else if (j == 3) {
                            Scheme.setNumber(Integer.parseInt(value));
                        } else if (j == 4) {
                            Scheme.setInstallNumber(Integer.parseInt(value));
                        } else if (j == 5) {
                            Scheme.setDebugNumber(Integer.parseInt(value));
                        } else if (j == 6) {
                            Scheme.setNotInstalled(Integer.parseInt(value));
                        } else if (j == 7) {
                            Scheme.setUnregulated(Integer.parseInt(value));
                        } else if (j == 8) {
                            Scheme.setUnissued(Integer.parseInt(value));
                        }
                    }
                }
                if (Scheme.getProjectId() != null && Scheme.getProductId() != null) {
                    schemeService.insert(Scheme);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
