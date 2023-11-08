package com.yunqi.common.mapper;

import com.yunqi.common.entity.StTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StTimeMapper {
    List<StTime> selectId(Integer projectId);
}
