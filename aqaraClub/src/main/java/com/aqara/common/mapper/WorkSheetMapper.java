package com.aqara.common.mapper;

import com.aqara.common.entity.WorkSheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSheetMapper {
    List<WorkSheet> select();

    List<WorkSheet> selectTom();

    List<WorkSheet> selectNoComplete();

    void insert(WorkSheet WorkSheet);

    void update(WorkSheet WorkSheet);

    void delete(Integer id);

    void deleteData();
}
