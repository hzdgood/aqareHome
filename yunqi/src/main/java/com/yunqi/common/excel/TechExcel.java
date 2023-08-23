package com.yunqi.common.excel;

import com.yunqi.common.entity.Tech;
import com.yunqi.common.service.TechService;
import com.yunqi.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TechExcel {

    public static void TechExcel(File file, TechService TechService) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Tech Tech = new Tech();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String value = ExcelUtil.getCellValue(cell);
                        if (j == 0) {
                            Tech.setName(value);
                        } else if (j == 1){
                            Tech.setTelephone(value);
                        } else if (j == 2){
                            Tech.setStatus(value);
                        }
                    }
                }
                TechService.insert(Tech);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
