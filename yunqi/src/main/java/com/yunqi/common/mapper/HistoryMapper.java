package com.yunqi.common.mapper;

import com.yunqi.common.entity.Scheme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryMapper {
    List<Scheme> selectId(Integer id);

    void insert(Scheme scheme);

    void update(Scheme scheme);

    void delete(Integer id);
}