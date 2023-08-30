package com.yunqi.common.mapper;

import com.yunqi.common.entity.Commission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionMapper {
    List<Commission> select();

    void insert(Commission Commission);

    void update(Commission Commission);

    void delete(Integer id);
}
