package com.yunqi.common.mapper;

import com.yunqi.common.entity.Logging;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggingMapper {
    List<Logging> select();
    void insert(Logging Logging);
}
