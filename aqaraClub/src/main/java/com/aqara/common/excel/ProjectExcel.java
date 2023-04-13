package com.aqara.common.excel;

import com.aqara.common.entity.Project;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExcel {
    public static List<Project> projectExcel(MultipartFile file) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            // 两种方式读取工作表
            // Sheet sheet=workbook.getSheet("Sheet0");
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            List<Project> list = new ArrayList<Project>();
            for (int i = 1; i <= lastRowNum; i++) {
                Project Project = new Project();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null || cell.equals(null)) {

                    } else {
                        if (j == 0) {
                            Project.setCode(cell.getStringCellValue());
                        } else if (j == 1) {
                            Project.setCurrrentTask(cell.getStringCellValue());
                        } else if (j == 2) {
                            Project.setDecorationProgress(cell.getStringCellValue());
                        } else if (j == 3) {
                            Project.setSaleConfirm(cell.getStringCellValue());
                        } else if (j == 4) {
                            Project.setEnd(cell.getStringCellValue());
                        } else if (j == 7) {
                            Project.setProjectName(cell.getStringCellValue());
                        } else if (j == 8) {
                            Project.setProjectTelephone(cell.getStringCellValue());
                        } else if (j == 9) {
                            Project.setProjectType(cell.getStringCellValue());
                        } else if (j == 19) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Project.setCreateTime(data);
                        } else if (j == 21) {
                            Project.setUpdateName(cell.getStringCellValue());
                        } else if (j == 22) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Project.setUpdateTime(data);
                        }
                    }
                }
                list.add(Project);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
