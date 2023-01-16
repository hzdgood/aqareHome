package com.aqara.common.mapper;

import com.aqara.common.entity.Collent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollentMapper {

    List<Collent> select(String code, String startTime, String endTime);

    List<Collent> currentData();

    List<Collent> weekData();

    void insert(Collent collent);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);

}
