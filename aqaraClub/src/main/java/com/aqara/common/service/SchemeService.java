package com.aqara.common.service;

import com.aqara.common.entity.Scheme;
import com.aqara.common.mapper.SchemeMapper;
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

    public void insert(Scheme scheme) {
        schemeMapper.insert(scheme);
    }

    public void insertToExcel(Scheme scheme) {
        schemeMapper.insertToExcel(scheme);
    }

    public void delete(Integer id) {
        schemeMapper.delete(id);
    }
}
