package com.aqara.common.service;

import com.aqara.common.entity.WorkSheet;
import com.aqara.common.mapper.WorkSheetMapper;
import com.aqara.common.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkSheetService {
    private WorkSheetMapper WorkSheetMapper;

    @Autowired
    public void setMapper(WorkSheetMapper WorkSheetMapper) {
        this.WorkSheetMapper = WorkSheetMapper;
    }

    public List<WorkSheet> selectId(String itemId) {
        return WorkSheetMapper.selectId(itemId);
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
