package com.aqara.common.excel;

import com.aqara.common.entity.Survey;
import com.aqara.common.service.SurveyService;
import com.aqara.common.utils.ExcelUtil;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SurveyExcel {
    public static void surveyExcel(File file, SurveyService SurveyService) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                    .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                    .open(fileInputStream);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Survey Survey = new Survey();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 1) {
                            Survey.setCode(cell.getStringCellValue());
                        } else if (j == 4) {
                            Survey.setSales(cell.getStringCellValue());
                        } else if (j == 6) {
                            Survey.setDepartment(cell.getStringCellValue());
                        } else if (j == 18) {
                            Survey.setAppointmentTime(cell.getDateCellValue());
                        } else if (j == 19) {
                            Survey.setEstimatedDuration(String.valueOf(cell.getNumericCellValue()));
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
                SurveyService.insert(Survey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
