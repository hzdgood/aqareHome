package com.yunqi.common.controller;

import com.yunqi.common.entity.Sales;
import com.yunqi.common.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    private SalesService SalesService;

    @Autowired
    public void setMapper(SalesService SalesService) {
        this.SalesService = SalesService;
    }


    @CrossOrigin
    @RequestMapping("/select")
    public List<Sales> select(Sales Sales) {
        return SalesService.select();
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Sales Sales) {
        SalesService.insert(Sales);
    }

}
