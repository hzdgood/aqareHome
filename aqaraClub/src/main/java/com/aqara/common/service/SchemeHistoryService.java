package com.aqara.common.service;

import com.aqara.common.entity.SchemeHistory;
import com.aqara.common.mapper.SchemeHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemeHistoryService {
    @Autowired
    SchemeHistoryMapper SchemeHistoryMapper;

    public List<SchemeHistory> select() {
        return SchemeHistoryMapper.select();
    }

    public void insert(SchemeHistory SchemeHistory) {
        SchemeHistoryMapper.insert(SchemeHistory);
    }

    public void delete(Integer id) {
        SchemeHistoryMapper.delete(id);
    }
}
