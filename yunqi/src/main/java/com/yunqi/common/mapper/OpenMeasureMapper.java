package com.yunqi.common.mapper;

import com.yunqi.common.entity.OpenMeasure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenMeasureMapper {
    List<OpenMeasure> select(OpenMeasure Login);

    void insert(OpenMeasure OpenMeasure);

    void update(OpenMeasure OpenMeasure);

    void delete(Integer id);
}
