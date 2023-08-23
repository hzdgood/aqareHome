package com.yunqi.common.mapper;


import com.yunqi.common.entity.Sales;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesMapper {
    List<Sales> select();

    void insert(Sales Sales);

    void update(Sales scheme);

    void delete(Integer id);
}
