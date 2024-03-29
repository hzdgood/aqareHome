package com.yunqi.common.service;

import com.yunqi.common.entity.Tech;
import com.yunqi.common.mapper.TechMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechService {
    private TechMapper TechMapper;

    @Autowired
    public void setMapper(TechMapper TechMapper) {
        this.TechMapper = TechMapper;
    }

    public List<Tech> select() {
        return TechMapper.select();
    }

    public List<Tech> selectId(Integer id) {
        return TechMapper.selectId(id);
    }
}
