package com.yunqi.common.mapper;

import com.yunqi.common.entity.Scheme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemeMapper {
    List<Scheme> select();

    List<Scheme> selectItemId(String itemId);

    void insert(Scheme scheme);

    void update(Scheme scheme);

    void delete(Integer id);
}
