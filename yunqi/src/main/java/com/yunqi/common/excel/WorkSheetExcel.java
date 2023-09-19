package com.yunqi.common.excel;

import com.yunqi.common.entity.Project;
import com.yunqi.common.entity.Tech;
import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.service.WorkSheetService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class WorkSheetExcel {
    public static void WorkSheetToExcel(File file, WorkSheetService workSheetService, List<Project> project, List<Tech> tech) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
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
                            StringBuilder techIds = new StringBuilder();
                            String str = null;
                            String[] obj = value.split("、");
                            if (obj.length > 1) {
                                for (String s : obj) {
                                    for (Tech techs : tech) {
                                        if (techs.getName().equals(s)) {
                                            techIds.append(techs.getId()).append(",");
                                        }
                                    }
                                    str = techIds.substring(0, techIds.length() - 1);
                                }
                            } else {
                                for (Tech techs : tech) {
                                    if (techs.getName().equals(value)) {
                                        str = "" + techs.getId();
                                    }
                                }
                            }
                            if (str != null) {
                                WorkSheet.setTechIds(str);
                            }
                        } else if (j == 1) {
                            for (Project p : project) {
                                if (p.getTelephone().equals(value)) {
                                    WorkSheet.setProjectId(p.getId());
                                }
                            }
                        } else if (j == 3) {
                            WorkSheet.setDateOfVisit(cell.getDateCellValue());
                        } else if (j == 4) {
                            WorkSheet.setType(value);
                        } else if (j == 5) {
                            WorkSheet.setStatus(value);
                        } else if (j == 6) {
                            WorkSheet.setSchedule(value);
                        } else if (j == 7) {
                            if (!value.isEmpty()) {
                                WorkSheet.setSignTime(cell.getDateCellValue());
                            }
                        } else if (j == 8) {
                            if (!value.isEmpty()) {
                                WorkSheet.setWriteTime(cell.getDateCellValue());
                            }
                        } else if (j == 9) {
                            WorkSheet.setActualWork(value);
                        }
                    }
                }
                if (WorkSheet.getProjectId() != null) {
                    workSheetService.insert(WorkSheet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
