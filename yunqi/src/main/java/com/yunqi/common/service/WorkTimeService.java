package com.yunqi.common.service;

import com.yunqi.common.entity.WorkTime;
import com.yunqi.common.mapper.WorkTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTimeService {
    private WorkTimeMapper WorkTimeMapper;

    @Autowired
    public void setMapper(WorkTimeMapper WorkTimeMapper) {
        this.WorkTimeMapper = WorkTimeMapper;
    }

    public List<WorkTime> select(WorkTime WorkTime) {
        return WorkTimeMapper.select(WorkTime);
    }

    public void insert(WorkTime WorkTime) {
        WorkTimeMapper.insert(WorkTime);
    }

    public void update(WorkTime WorkTime) {
        WorkTimeMapper.update(WorkTime);
    }

    public void sign(WorkTime WorkTime) {
        WorkTimeMapper.sign(WorkTime);
    }

    public void depart(WorkTime WorkTime) {
        WorkTimeMapper.depart(WorkTime);
    }

    public void delete(Integer id, String updateName) {
        WorkTimeMapper.delete(id, updateName);
    }

    public void complete(Integer id, String updateName) {
        WorkTimeMapper.complete(id, updateName);
    }
}
