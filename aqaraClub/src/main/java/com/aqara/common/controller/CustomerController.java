package com.aqara.common.controller;

import com.aqara.common.entity.Customer;
import com.aqara.common.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setMapper(CustomerService customerService) {
        this.customerService = customerService;
    }

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
    @RequestMapping("/synchronize")
    public void synchronize(Customer Customer) {
        String itemId = Customer.getItemId();
        List<Customer> list = customerService.selectId(itemId);
        if (list.size() == 0) {
            customerService.insert(Customer);
        } else {
            customerService.delete(itemId);
            customerService.insert(Customer);
        }
    }
}
