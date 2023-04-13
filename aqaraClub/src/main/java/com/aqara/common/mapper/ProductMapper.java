package com.aqara.common.mapper;

import com.aqara.common.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Product> select();

    void insert(Product Product);

    void update(Product Product);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);
}
