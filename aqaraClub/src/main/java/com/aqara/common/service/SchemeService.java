package com.aqara.common.service;

import com.aqara.common.entity.Scheme;
import com.aqara.common.mapper.SchemeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
