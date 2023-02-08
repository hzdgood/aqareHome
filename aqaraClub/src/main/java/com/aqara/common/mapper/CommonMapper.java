package com.aqara.common.mapper;

import com.aqara.common.entity.Common;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonMapper {

    List<Common> select(String name);

    void insert(Common Common);

}
