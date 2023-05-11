package com.aqara.common.controller;

import com.aqara.common.service.*;
import com.aqara.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService ProductService;
    @CrossOrigin
    @RequestMapping("/synchronize")
    public void synchronize(Product Product) {
        String code = Product.getCode();
        List<Product> list = ProductService.select(code);
        if(list.size() == 0) {
            ProductService.insert(Product);
        } else {
            ProductService.update(Product);
            ProductService.insert(Product);
        }
    }
}
