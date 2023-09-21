package com.yunqi.common.service;

import com.yunqi.common.entity.WriterDetail;
import com.yunqi.common.mapper.WriterDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterDetailService {

    private WriterDetailMapper WriterDetailMapper;

    @Autowired
    public void setMapper(WriterDetailMapper WriterDetailMapper) {
        this.WriterDetailMapper = WriterDetailMapper;
    }

    public void insert(WriterDetail WriterDetail) {
        WriterDetailMapper.insert(WriterDetail);
    }
    public void update(WriterDetail WriterDetail) {
        WriterDetailMapper.update(WriterDetail);
    }
}
