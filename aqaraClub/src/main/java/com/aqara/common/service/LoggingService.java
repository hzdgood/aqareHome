package com.aqara.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqara.common.entity.Logging;
import com.aqara.common.mapper.LoggingMapper;

@Service
public class LoggingService {
    @Autowired
    LoggingMapper LoggingMapper;

    public void insert(Logging log) {
        LoggingMapper.insert(log);
    }

    public List<Logging> select(String name, String custom) {
        return LoggingMapper.select(name, custom);
    }
}
