package com.yunqi.common.service;

import com.yunqi.common.entity.stTime;
import com.yunqi.common.mapper.stTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stTimeService {
    private stTimeMapper stTimeMapper;

    @Autowired
    public void setMapper(stTimeMapper stTimeMapper) {
        this.stTimeMapper = stTimeMapper;
    }

    public List<stTime> selectId(Integer projectId) {
        return stTimeMapper.selectId(projectId);
    }
}
