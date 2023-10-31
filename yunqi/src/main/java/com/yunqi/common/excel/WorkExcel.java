package com.yunqi.common.excel;

import com.yunqi.common.entity.Project;
import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.service.WorkSheetService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class WorkExcel {
    public static void workExcel(File file, WorkSheetService workSheetService, List<Project> project) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                WorkSheet WorkSheet = new WorkSheet();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            WorkSheet.setDateOfVisit(cell.getDateCellValue());
                        } else if (j == 2) {
                            for (Project p : project) {
                                if (p.getItemId().equals(value)) {
                                    WorkSheet.setProjectId(p.getId());
                                }
                            }
                        } else if (j == 3) {
                            WorkSheet.setType(value);
                        } else if (j == 4) {
                            WorkSheet.setTechIds(value);
                        } else if (j == 5) {
                            WorkSheet.setStatus(value);
                        } else if (j == 6) {
                            WorkSheet.setWorkSummary(value);
                        } else if (j == 7) {
                            WorkSheet.setVisitNode(value);
                        }
                        WorkSheet.setHeadName("管理员");
                        WorkSheet.setCreateName("初始化");
                    }
                }
                workSheetService.insert(WorkSheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

