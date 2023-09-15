package com.yunqi.common.service;

import com.yunqi.common.entity.Writer;
import com.yunqi.common.mapper.WriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterService {

    private WriterMapper WriterMapper;

    @Autowired
    public void setMapper(WriterMapper WriterMapper) {
        this.WriterMapper = WriterMapper;
    }

    public void insert(Writer Writer) {
        WriterMapper.insert(Writer);
    }

    public void update(Writer Writer) {
        WriterMapper.update(Writer);
    }
}
