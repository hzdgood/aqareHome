package com.yunqi.common.service;

import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.mapper.WorkSheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkSheetService {
    private WorkSheetMapper WorkSheetMapper;

    @Autowired
    public void setMapper(WorkSheetMapper WorkSheetMapper) {
        this.WorkSheetMapper = WorkSheetMapper;
    }

    public List<WorkSheet> select(WorkSheet WorkSheet) {
        return WorkSheetMapper.select(WorkSheet);
    }

    public void insert(WorkSheet WorkSheet) {
        WorkSheetMapper.insert(WorkSheet);
    }

    public void update(WorkSheet WorkSheet) {
        WorkSheetMapper.update(WorkSheet);
    }

    public void updateInfo(WorkSheet WorkSheet) {
        WorkSheetMapper.updateInfo(WorkSheet);
    }

    public void updatePerson(WorkSheet WorkSheet) {
        WorkSheetMapper.updatePerson(WorkSheet);
    }

    public void updateHead(WorkSheet WorkSheet) {
        WorkSheetMapper.updateHead(WorkSheet);
    }

    public void delete(Integer id, String updateName) {
        WorkSheetMapper.delete(id, updateName);
    }

    public void complete(Integer workId) {
        WorkSheetMapper.complete(workId);
    }

    public void updateStatus(Integer id, String status, String updateName) {
        WorkSheetMapper.updateStatus(id, status, updateName);
    }
}
