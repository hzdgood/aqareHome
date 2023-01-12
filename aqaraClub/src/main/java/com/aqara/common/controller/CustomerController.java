package com.aqara.common.controller;

import com.aqara.common.entity.Customer;
import com.aqara.common.service.CustomerService;
import com.aqara.common.utils.ReadExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @CrossOrigin
    @RequestMapping("/select")
    public List<Customer> getSelect(@RequestBody String sales, String depart, String startTime, String endTime) {
        return customerService.select(sales, depart, startTime, endTime);
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(@RequestBody Customer Customer) {
        customerService.insert(Customer);
    }

    @CrossOrigin
    @RequestMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        List<Customer> list = ReadExcelUtil.customerExcel(file);
        for (int i = 0; i < list.size(); i++) {
            Customer Customer = list.get(i);
            customerService.insert(Customer);
        }
    }
}
