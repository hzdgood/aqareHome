package com.aqara.common.mapper;

import com.aqara.common.entity.SchemeHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemeHistoryMapper {
    List<SchemeHistory> select();

    void insert(SchemeHistory SchemeHistory);

    void delete(Integer id);
}
