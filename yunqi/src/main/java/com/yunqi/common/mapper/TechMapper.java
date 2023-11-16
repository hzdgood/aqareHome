package com.yunqi.common.mapper;

import com.yunqi.common.entity.Tech;

import java.util.List;

public interface TechMapper {
    List<Tech> select();
    List<Tech> selectId(Integer id);
}
