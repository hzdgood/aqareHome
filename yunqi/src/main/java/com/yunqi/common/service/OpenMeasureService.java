package com.yunqi.common.service;

import com.yunqi.common.entity.OpenMeasure;
import com.yunqi.common.mapper.OpenMeasureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenMeasureService {
    private OpenMeasureMapper OpenMeasureMapper;

    @Autowired
    public void setMapper(OpenMeasureMapper OpenMeasureMapper) {
        this.OpenMeasureMapper = OpenMeasureMapper;
    }

    public List<OpenMeasure> select(OpenMeasure OpenMeasure) {
        return OpenMeasureMapper.select(OpenMeasure);
    }

    public void insert(OpenMeasure OpenMeasure) {
        OpenMeasureMapper.insert(OpenMeasure);
    }

    public void update(OpenMeasure OpenMeasure) {
        OpenMeasureMapper.update(OpenMeasure);
    }

    public void delete(Integer id) {
        OpenMeasureMapper.delete(id);
    }
}
