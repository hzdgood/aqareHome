package com.yunqi.common.service;

import com.yunqi.common.entity.SyncScheme;
import com.yunqi.common.mapper.SyncSchemeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncSchemeService {

    private SyncSchemeMapper SyncSchemeMapper;

    @Autowired
    public void setMapper(SyncSchemeMapper SyncSchemeMapper) {
        this.SyncSchemeMapper = SyncSchemeMapper;
    }

    public void insert(SyncScheme syncScheme) {
        SyncSchemeMapper.insert(syncScheme);
    }

    public void delete(Integer id) {
        SyncSchemeMapper.delete(id);
    }

    public List<SyncScheme> select() {
        return SyncSchemeMapper.select();
    }
}
