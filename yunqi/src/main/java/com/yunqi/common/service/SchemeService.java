package com.yunqi.common.service;

import com.yunqi.common.entity.Scheme;
import com.yunqi.common.mapper.SchemeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemeService {
    private SchemeMapper schemeMapper;

    @Autowired
    public void setMapper(SchemeMapper schemeMapper) {
        this.schemeMapper = schemeMapper;
    }

    public List<Scheme> select() {
        return schemeMapper.select();
    }

    public List<Scheme> selectId(Integer id) {
        return schemeMapper.selectId(id);
    }

    public List<Scheme> selectItem(String itemId) {
        return schemeMapper.selectItem(itemId);
    }

    public void insert(Scheme scheme) {
        schemeMapper.insert(scheme);
    }

    public void update(Scheme scheme) {
        schemeMapper.update(scheme);
    }

    public void updateId(Scheme scheme) {
        schemeMapper.updateId(scheme);
    }
}
