package com.aqara.common.excel;

import com.aqara.common.entity.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerExcel {
    public static List<Customer> customerExcel(MultipartFile files) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
            // 两种方式读取工作表
            // Sheet sheet=workbook.getSheet("Sheet0");
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            List<Customer> list = new ArrayList<Customer>();
            for (int i = 1; i <= lastRowNum; i++) {
                Customer customer = new Customer();
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null || cell.equals(null)) {
                    } else {
                        if (j == 1) {
                            customer.setName(cell.getStringCellValue());
                        } else if (j == 2) {
                            customer.setTelephone(cell.getStringCellValue());
                        } else if (j == 4) {
                            customer.setSales(cell.getStringCellValue());
                        } else if (j == 6) {
                            customer.setDepartment(cell.getStringCellValue());
                        } else if (j == 9) {
                            customer.setSex(cell.getStringCellValue());
                        } else if (j == 10) {
                            customer.setSource(cell.getStringCellValue());
                        } else if (j == 11) {
                            customer.setHouseType(cell.getStringCellValue());
                        } else if (j == 12) {
                            customer.setHouseState(cell.getStringCellValue());
                        } else if (j == 13) {
                            customer.setPersonnelAttr(cell.getStringCellValue());
                        } else if (j == 14) {
                            customer.setCustomerAttr(cell.getStringCellValue());
                        } else if (j == 15) {
                            customer.setCustomerStage(cell.getStringCellValue());
                        } else if (j == 16) {
                            customer.setSPcustomer(cell.getStringCellValue());
                        } else if (j == 17) {
                            customer.setHouseDemand(cell.getStringCellValue());
                        } else if (j == 18) {
                            customer.setLossStatus(cell.getStringCellValue());
                        } else if (j == 19) {
                            customer.setWeiXin(cell.getStringCellValue());
                        } else if (j == 26) {
                            customer.setUserID(cell.getStringCellValue());
                        } else if (j == 27) {
                            customer.setReleWeixin(cell.getStringCellValue());
                        } else if (j == 29) {
                            customer.setCreateName(cell.getStringCellValue());
                        } else if (j == 30) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            customer.setCreateTime(data);
                        } else if (j == 31) {
                            customer.setUpdateName(cell.getStringCellValue());
                        } else if (j == 32) {
                            Date data = simpleDateFormat.parse(cell.getStringCellValue());
                            customer.setUpdateTime(data);
                        }
                    }
                }
                list.add(customer);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
