package com.yunqi.common.service;

import com.yunqi.common.entity.Writer;
import com.yunqi.common.mapper.WriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {

    private WriterMapper WriterMapper;

    @Autowired
    public void setMapper(WriterMapper WriterMapper) {
        this.WriterMapper = WriterMapper;
    }

    public List<Writer> selectNumber(Writer Writer) {
        return WriterMapper.selectNumber(Writer);
    }

    public void insert(Writer Writer) {
        WriterMapper.insert(Writer);
    }

    public void update(Writer Writer) {
        WriterMapper.update(Writer);
    }

    public List<Writer> select(Writer Writer) { return WriterMapper.select(Writer); }

    public void simpleWriter(Writer writer) {
        WriterMapper.simpleWriter(writer);
    }

    public void insertHead(Writer writer) {
        WriterMapper.insertHead(writer);
    }
}
