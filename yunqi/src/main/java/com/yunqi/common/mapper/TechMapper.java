package com.yunqi.common.mapper;

import com.yunqi.common.entity.Tech;

import java.util.List;

public interface TechMapper {
    List<Tech> select();
    List<Tech> selectName(String name);

    void insert(Tech Tech);

    void update(Tech Tech);

    void delete(Integer id);
}
