package com.aqara.common.excel;

import com.aqara.common.entity.Survey;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyExcel {
    public static List<Survey> surveyExcel(MultipartFile file) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            // 两种方式读取工作表
            // Sheet sheet=workbook.getSheet("Sheet0");
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            List<Survey> list = new ArrayList<Survey>();
            for (int i = 1; i <= lastRowNum; i++) {
                Survey Survey = new Survey();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null || cell.equals(null)) {

                    } else {
                        if (j == 1) {
                            Survey.setCode(cell.getStringCellValue());
                        } else if (j == 4) {
                            Survey.setSales(cell.getStringCellValue());
                        } else if (j == 6) {
                            Survey.setDepartment(cell.getStringCellValue());
                        } else if (j == 18) {
                            Survey.setAppointmentTime(cell.getDateCellValue());
                        } else if (j == 19) {
                            Survey.setEstimatedDuration(cell.getNumericCellValue() + "");
                        } else if (j == 20) {
                            Survey.setParticipants(cell.getStringCellValue());
                        } else if (j == 22) {
                            Survey.setSurveyPictures(cell.getStringCellValue());
                        } else if (j == 23) {
                            Survey.setCreateName(cell.getStringCellValue());
                        } else if (j == 24) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Survey.setCreateTime(data);
                        } else if (j == 25) {
                            Survey.setUpdateName(cell.getStringCellValue());
                        } else if (j == 26) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Survey.setUpdateTime(data);
                        }
                    }
                }
                list.add(Survey);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
