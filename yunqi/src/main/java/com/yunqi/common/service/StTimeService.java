package com.yunqi.common.service;

import com.yunqi.common.entity.StTime;
import com.yunqi.common.mapper.StTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StTimeService {
    private StTimeMapper StTimeMapper;

    @Autowired
    public void setMapper(StTimeMapper StTimeMapper) {
        this.StTimeMapper = StTimeMapper;
    }

    public List<StTime> selectId(Integer projectId) {
        return StTimeMapper.selectId(projectId);
    }
}
