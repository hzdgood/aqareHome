package com.yunqi.common.excel;

public class TechExcel {

//    public static void TechExcel(File file, TechService TechService) throws FileNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream(file);
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
//            Sheet sheet = workbook.getSheetAt(0);
//            int lastRowNum = sheet.getLastRowNum();
//            for (int i = 1; i <= lastRowNum; i++) {
//                Tech Tech = new Tech();
//                Row row = sheet.getRow(i);
//                int lastCellNum = row.getLastCellNum();
//                for (int j = 0; j < lastCellNum; j++) {
//                    Cell cell = row.getCell(j);
//                    if (cell != null) {
//                        String value = ExcelUtil.getCellValue(cell);
//                        if (j == 0) {
//                            Tech.setName(value);
//                        }
//                    }
//                }
//                TechService.insert(Tech);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
