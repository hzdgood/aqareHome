package com.yunqi.common.mapper;

import com.yunqi.common.entity.WorkSheet;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSheetMapper {
    void insert(WorkSheet WorkSheet);

    void update(WorkSheet WorkSheet);

    void updateInfo(WorkSheet WorkSheet);

    void delete(Integer id, String updateName);

    void complete(Integer workId);
}
