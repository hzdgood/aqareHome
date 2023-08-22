package com.yunqi.common.mapper;

import com.yunqi.common.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Product> select(String code);

    void insert(Product Product);

    void update(Product Product);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);
}
