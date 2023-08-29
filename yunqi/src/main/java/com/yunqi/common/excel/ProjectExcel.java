package com.yunqi.common.excel;

import com.yunqi.common.entity.*;
import com.yunqi.common.service.ProjectService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ProjectExcel {
    public static void ProjectExcel(File file, ProjectService ProjectService, List<Tech> tech, List<Sales> sale) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Project Project = new Project();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            for (Sales sales : sale) {
                                if (sales.getName().equals(value)) {
                                    Project.setSaleId(sales.getId());
                                }
                            }
                        } else if (j == 1) {
                            for (Tech techs : tech) {
                                if (techs.getName().equals(value)) {
                                    Project.setTechID(techs.getId());
                                }
                            }
                        } else if (j == 2) {
                            Project.setName(value);
                        } else if (j == 3) {
                            Project.setTelephone(value);
                        } else if (j == 4) {
                            Project.setAddress(value);
                        } else if (j == 5) {
                            Project.setType(value);
                        } else if (j == 6) {
                            Project.setNode(value);
                        } else if (j == 7) {
                            Project.setStatus(value);
                        } else if (j == 8) {
                            Project.setHouseState(value);
                        } else if (j == 9) {
                            Project.setSituation(value);
                        } else if (j == 10) {
                            Project.setRegion(value);
                        } else if (j == 11) {
                            Project.setSchedule(value);
                        }
                    }
                }
                // System.out.printf(Project.toString());
                ProjectService.insert(Project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
