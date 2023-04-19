package com.aqara.common.excel;

import com.aqara.common.entity.Project;
import com.aqara.common.service.ProjectService;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExcel {
    public static List<Project> projectExcel(File file, ProjectService ProjectService) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                    .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                    .open(fileInputStream);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
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
                    if (cell != null) {
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
                        } else if (j == 5) {
                            Project.setProjectName(cell.getStringCellValue());
                        } else if (j == 6) {
                            Project.setProjectTelephone(cell.getStringCellValue());
                        } else if (j == 7) {
                            Project.setProjectType(cell.getStringCellValue());
                        } else if (j == 8) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Project.setCreateTime(data);
                        } else if (j == 9) {
                            Project.setUpdateName(cell.getStringCellValue());
                        } else if (j == 10) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Project.setUpdateTime(data);
                        }
                    }
                }
                ProjectService.insert(Project);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
