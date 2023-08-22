package com.yunqi.common.controller;

import com.yunqi.common.entity.Product;
import com.yunqi.common.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService ProductService;

    @Autowired
    public void setMapper(ProductService ProductService) {
        this.ProductService = ProductService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Product Product) {
        ProductService.insert(Product);
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(Product Product) {
        ProductService.update(Product);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    public void delete(Integer id) {
        ProductService.delete(id);
    }
}
