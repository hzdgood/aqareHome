package com.aqara.common.mapper;

import com.aqara.common.entity.Census;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CensusMapper {
    List<Census> select();

    List<Census> weekData();

    void insert(Census Census);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);
}
