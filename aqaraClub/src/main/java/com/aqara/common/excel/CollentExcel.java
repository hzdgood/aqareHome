package com.aqara.common.excel;

import com.aqara.common.entity.Collent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollentExcel {
    public static List<Collent> CollentExcel(MultipartFile file) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            // 两种方式读取工作表
            // Sheet sheet=workbook.getSheet("Sheet0");
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            List<Collent> list = new ArrayList<Collent>();
            for (int i = 1; i <= lastRowNum; i++) {
                Collent Collent = new Collent();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null || cell.equals(null)) {

                    } else {
                        if (j == 3) {
                            Collent.setCode(cell.getStringCellValue());
                        } else if (j == 16) {
                            Collent.setProjectStatus(cell.getStringCellValue());
                        } else if (j == 17) {
                            Collent.setTotalReceipts(cell.getStringCellValue());
                        } else if (j == 18) {
                            Collent.setFinalPrice(cell.getStringCellValue());
                        } else if (j == 19) {
                            Collent.setChangePrice(cell.getStringCellValue());
                        } else if (j == 22) {
                            Collent.setCollectionStatus(cell.getStringCellValue());
                        } else if (j == 23) {
                            Collent.setRefundType(cell.getStringCellValue());
                        } else if (j == 24) {
                            Collent.setPaymentMethod(cell.getStringCellValue());
                        } else if (j == 26) {
                            Collent.setCollection(cell.getStringCellValue());
                        } else if (j == 28) {
                            Collent.setQuotationID(cell.getStringCellValue());
                        } else if (j == 33) {
                            Collent.setDocumentDate(cell.getDateCellValue());
                        } else if (j == 34) {
                            Collent.setSettlementDate(cell.getDateCellValue());
                        } else if (j == 36) {
                            Collent.setUploadVoucher(cell.getStringCellValue());
                        } else if (j == 40) {
                            Collent.setPayee(cell.getStringCellValue());
                        } else if (j == 54) {
                            Collent.setCrossCheck(cell.getStringCellValue());
                        } else if (j == 60) {
                            Collent.setCreateName(cell.getStringCellValue());
                        } else if (j == 61) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Collent.setCreateTime(data);
                        } else if (j == 62) {
                            Collent.setUpdateName(cell.getStringCellValue());
                        } else if (j == 63) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            Collent.setUpdateTime(data);
                        }

                    }
                }
                list.add(Collent);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
