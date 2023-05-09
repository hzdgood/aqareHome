package com.aqara.common.excel;

import com.aqara.common.entity.Customer;
import com.aqara.common.service.CustomerService;
import com.aqara.common.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomerExcel {
    public static void customerExcel(File file, CustomerService CustomerService) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        List<Customer> list = new ArrayList<Customer>();
        for (int i = 1; i <= lastRowNum; i++) {
            Customer customer = new Customer();
            Row row = sheet.getRow(i);
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    String value = ExcelUtil.getCellValue(cell);
                    if (j == 0) {
                        customer.setItemId(value);
                    } else if (j == 1) {
                        customer.setName(value);
                    } else if (j == 2) {
                        customer.setTelephone(value);
                    } else if (j == 3) {
                        customer.setSales(value);
                    } else if (j == 4) {
                        customer.setSex(value);
                    } else if (j == 5) {
                        customer.setSource(value);
                    } else if (j == 6) {
                        customer.setHouseType(value);
                    } else if (j == 7) {
                        customer.setHouseState(value);
                    } else if (j == 8) {
                        customer.setPersonnelAttr(value);
                    } else if (j == 9) {
                        customer.setCustomerAttr(value);
                    } else if (j == 10) {
                        customer.setCustomerStage(value);
                    } else if (j == 11) {
                        customer.setSPcustomer(value);
                    } else if (j == 12) {
                        customer.setHouseDemand(value);
                    } else if (j == 13) {
                        customer.setLossStatus(value);
                    } else if (j == 14) {
                        customer.setWeiXin(value);
                    } else if (j == 15) {
                        customer.setUserID(value);
                    } else if (j == 16) {
                        customer.setReleWeixin(value);
                    }
                }
            }
            CustomerService.insert(customer);
        }
    }
}
