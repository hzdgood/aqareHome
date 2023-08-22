package com.yunqi.common.mapper;

import com.yunqi.common.entity.WorkSheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSheetMapper {
    List<WorkSheet> selectNow();

    List<WorkSheet> selectId(String itemId);

    List<WorkSheet> selectToday();

    List<WorkSheet> selectTom();

    List<WorkSheet> selectNotVisit();

    List<WorkSheet> getNotComplete();

    void insert(WorkSheet WorkSheet);

    void update(WorkSheet WorkSheet);

    void delete(Integer id);
}
