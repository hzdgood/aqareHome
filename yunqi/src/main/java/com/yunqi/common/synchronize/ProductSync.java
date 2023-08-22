package com.yunqi.common.synchronize;

import com.yunqi.common.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yunqi.common.entity.Product;
import java.util.List;

@RestController
@RequestMapping("/synchronize")
public class ProductSync {
    private ProductService ProductService;

    @Autowired
    public void setMapper(ProductService ProductService) {
        this.ProductService = ProductService;
    }

    @CrossOrigin
    @RequestMapping("/product")
    public void synchronize(Product Product) {
        String code = Product.getCode();
        List<Product> list = ProductService.select(code);
        if (list.size() == 0) {
            ProductService.insert(Product);
        } else {
            ProductService.update(Product);
            ProductService.insert(Product);
        }
    }
}
