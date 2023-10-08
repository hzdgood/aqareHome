package com.yunqi.common.service;

import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.mapper.WorkSheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkSheetService {
    private WorkSheetMapper WorkSheetMapper;

    @Autowired
    public void setMapper(WorkSheetMapper WorkSheetMapper) {
        this.WorkSheetMapper = WorkSheetMapper;
    }

    public WorkSheet insert(WorkSheet WorkSheet) {
        return WorkSheetMapper.insert(WorkSheet);
    }

    public void update(WorkSheet WorkSheet) {
        WorkSheetMapper.update(WorkSheet);
    }

    public void updateInfo(WorkSheet WorkSheet) {
        WorkSheetMapper.updateInfo(WorkSheet);
    }

    public void delete(Integer id, String updateName) {
        WorkSheetMapper.delete(id, updateName);
    }

    public void complete(Integer workId, String techIds) {
        WorkSheetMapper.complete(workId, techIds);
    }
}
