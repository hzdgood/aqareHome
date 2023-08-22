package com.yunqi.common.mapper;

import com.yunqi.common.entity.Scheme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemeMapper {
    List<Scheme> select();

    void insert(Scheme scheme);

    void insertToExcel(Scheme scheme);

    void delete(Integer id);

    void update(Scheme scheme);
}
