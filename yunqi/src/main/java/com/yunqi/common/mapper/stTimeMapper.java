package com.yunqi.common.mapper;

import com.yunqi.common.entity.stTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface stTimeMapper {
    List<stTime> selectId(Integer projectId);
}
