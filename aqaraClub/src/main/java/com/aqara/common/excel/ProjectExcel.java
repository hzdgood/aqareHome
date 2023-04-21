package com.aqara.common.excel;

import com.aqara.common.entity.Project;
import com.aqara.common.service.ProjectService;
import com.aqara.common.utils.ExcelUtil;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileInputStream;

public class ProjectExcel {
    public static void projectExcel(File file, ProjectService ProjectService) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                    .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                    .open(fileInputStream);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
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
                            Project.setItemId(value);
                        } else if (j == 1) {
                            Project.setTask(value);
                        } else if (j == 2) {
                            Project.setProgress(value);
                        } else if (j == 3) {
                            Project.setSaleConfirm(value);
                        } else if (j == 4) {
                            Project.setEnd(value);
                        } else if (j == 5) {
                            Project.setName(value);
                        } else if (j == 6) {
                            Project.setTelephone(value);
                        } else if (j == 7) {
                            Project.setDepartment(value);
                        } else if (j == 8) {
                            Project.setSales(value);
                        } else if (j == 9) {
                            Project.setTechId(value);
                        } else if (j == 10) {
                            Project.setProjectType(value);
                        } else if (j == 11) {
                            Project.setDesigner(value);
                        } else if (j == 12) {
                            Project.setIntention(value);
                        } else if (j == 13) {
                            Project.setTrack(value);
                        } else if (j == 14) {
                            Project.setPreferential(value);
                        } else if (j == 15) {
                            Project.setTotalPrice(value);
                        } else if (j == 16) {
                            Project.setFullPrice(value);
                        } else if (j == 17) {
                            Project.setTotalAmount(value);
                        } else if (j == 18) {
                            Project.setMainProject(value);
                        } else if (j == 19) {
                            Project.setGroupID(value);
                        } else if (j == 20) {
                            Project.setAddress(value);
                        }
                    }
                }
                ProjectService.insert(Project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
