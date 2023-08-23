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

    public List<WorkSheet> select() {
        return WorkSheetMapper.select();
    }

    public void insert(WorkSheet WorkSheet) {
        WorkSheetMapper.insert(WorkSheet);
    }

    public void update(WorkSheet WorkSheet) {
        WorkSheetMapper.update(WorkSheet);
    }

    public void delete(Integer id) {
        WorkSheetMapper.delete(id);
    }
}
