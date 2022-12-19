package com.aqara.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.Scheme;
import com.aqara.common.mapper.SchemeMapper;

@Service
public class SchemeService {
    @Autowired
    SchemeMapper schemeMapper;

    public List<Scheme> select() {
        return schemeMapper.select();
    }

    public void insert(Scheme scheme) {
        schemeMapper.insert(scheme);
    }

    public void delete(Integer id) {
        schemeMapper.delete(id);
    }
}
