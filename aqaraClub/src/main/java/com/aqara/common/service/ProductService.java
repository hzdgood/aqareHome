package com.aqara.common.service;

import com.aqara.common.entity.Product;
import com.aqara.common.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper ProductMapper;

    public List<Product> select() {
        return ProductMapper.select();
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

    public void deleteAll(List<Integer> ids) {
        ProductMapper.deleteAll(ids);
    }
}
