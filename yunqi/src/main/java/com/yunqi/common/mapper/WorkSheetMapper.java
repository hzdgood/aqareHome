package com.yunqi.common.mapper;

import com.yunqi.common.entity.WorkSheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSheetMapper {
    void insert(WorkSheet WorkSheet);

    void update(WorkSheet WorkSheet);

    void sign(Integer id);

    void write(Integer id);

    void delete(Integer id);
}
