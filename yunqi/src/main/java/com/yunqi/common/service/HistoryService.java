package com.yunqi.common.service;

import com.yunqi.common.entity.Scheme;
import com.yunqi.common.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private HistoryMapper HistoryMapper;

    @Autowired
    public void setMapper(HistoryMapper HistoryMapper) {
        this.HistoryMapper = HistoryMapper;
    }

    public List<Scheme> selectId(Integer id) {
        return HistoryMapper.selectId(id);
    }

    public void insert(Scheme scheme) {
        HistoryMapper.insert(scheme);
    }

    public void update(Scheme scheme) {
        HistoryMapper.update(scheme);
    }

    void delete(Integer id) { HistoryMapper.delete(id); }
}
