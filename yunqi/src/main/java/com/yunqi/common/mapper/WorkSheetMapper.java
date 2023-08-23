package com.yunqi.common.mapper;

import com.yunqi.common.entity.WorkSheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSheetMapper {
    List<WorkSheet> select();

    void insert(WorkSheet WorkSheet);

    void update(WorkSheet WorkSheet);

    void delete(Integer id);
}
