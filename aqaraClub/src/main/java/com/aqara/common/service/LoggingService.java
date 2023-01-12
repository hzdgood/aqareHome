package com.aqara.common.service;

import com.aqara.common.entity.Logging;
import com.aqara.common.mapper.LoggingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
