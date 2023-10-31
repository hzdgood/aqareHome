package com.yunqi.common.service;

import com.yunqi.common.entity.RollerMeasure;
import com.yunqi.common.mapper.RollerMeasureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerMeasureService {
    private RollerMeasureMapper RollerMeasureMapper;

    @Autowired
    public void setMapper(RollerMeasureMapper RollerMeasureMapper) {
        this.RollerMeasureMapper = RollerMeasureMapper;
    }

    public List<RollerMeasure> select(RollerMeasure RollerMeasure) {
        return RollerMeasureMapper.select(RollerMeasure);
    }

    public void insert(RollerMeasure RollerMeasure) {
        RollerMeasureMapper.insert(RollerMeasure);
    }

    public void delete(Integer id) {
        RollerMeasureMapper.delete(id);
    }
}
