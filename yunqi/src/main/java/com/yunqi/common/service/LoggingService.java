package com.yunqi.common.service;

import com.yunqi.common.entity.Logging;
import com.yunqi.common.mapper.LoggingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggingService {
    private LoggingMapper LoggingMapper;
    @Autowired
    public void setMapper(LoggingMapper LoggingMapper) {
        this.LoggingMapper = LoggingMapper;
    }

    public List<Logging> select() { return LoggingMapper.select(); }
    public void insert(Logging Logging) {
        LoggingMapper.insert(Logging);
    }
}
