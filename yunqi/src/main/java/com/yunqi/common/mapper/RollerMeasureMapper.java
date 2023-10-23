package com.yunqi.common.mapper;

import com.yunqi.common.entity.RollerMeasure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollerMeasureMapper {
    List<RollerMeasure> select(RollerMeasure RollerMeasure);

    void insert(RollerMeasure RollerMeasure);

    void update(RollerMeasure RollerMeasure);

    void delete(Integer id);
}
