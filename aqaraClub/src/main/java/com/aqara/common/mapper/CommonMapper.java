package com.aqara.common.mapper;

import com.aqara.common.entity.Common;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonMapper {

    List<Common> getValue(String name);

    void insert(Common Common);

    void delete(Integer id);

}
