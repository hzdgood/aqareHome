package com.yunqi.common.service;

import com.yunqi.common.entity.Product;
import com.yunqi.common.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductMapper ProductMapper;

    @Autowired
    public void setMapper(ProductMapper ProductMapper) {
        this.ProductMapper = ProductMapper;
    }

    public List<Product> select() {
        return ProductMapper.select();
    }

    public List<Product> selectItemId(String itemId) {
        return ProductMapper.selectItemId(itemId);
    }

    public void insert(Product Product) {
        ProductMapper.insert(Product);
    }

    public void update(Product Product) {
        ProductMapper.update(Product);
    }

    public void delete(Integer id) {
        ProductMapper.delete(id);
    }
}
