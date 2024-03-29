package com.aqara.common.mapper;

import com.aqara.common.entity.Collent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollentMapper {
    List<Collent> select(String code, String startTime, String endTime);

    List<Collent> currentData();

    void insert(Collent collent);

    void update(Collent collent);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);

    List<Collent> selectId(String itemId);
}
