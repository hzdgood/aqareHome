package com.yunqi.common.mapper;

import com.yunqi.common.entity.WorkSheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSheetMapper {
    List<WorkSheet> select(WorkSheet WorkSheet);

    void insert(WorkSheet WorkSheet);

    void update(WorkSheet WorkSheet);

    void updateInfo(WorkSheet WorkSheet);

    void updatePerson(WorkSheet WorkSheet);

    void updateHead(WorkSheet WorkSheet);

    void delete(Integer id, String updateName);

    void complete(Integer workId);

    void updateStatus(Integer id, String status, String updateName);
}
