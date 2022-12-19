package com.aqara.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Census;

@Repository
public interface CensusMapper {
    List<Census> select();

    List<Census> weekData();

    void insert(Census Census);

    void delete(Integer id);
}
