package com.yunqi.common.utils;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK: //空值
            case ERROR: //故障
                break;
            default:
                break;
        }
        return cellValue.trim();
    }
}
