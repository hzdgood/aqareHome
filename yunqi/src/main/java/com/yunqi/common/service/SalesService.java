package com.yunqi.common.service;

import com.yunqi.common.entity.Sales;
import com.yunqi.common.mapper.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    private SalesMapper SalesMapper;

    @Autowired
    public void setMapper(SalesMapper SalesMapper) {
        this.SalesMapper = SalesMapper;
    }

    public List<Sales> select() {
        return SalesMapper.select();
    }

    public void insert(Sales Sales) {
        SalesMapper.insert(Sales);
    }

    public void update(Sales Sales) {
        SalesMapper.update(Sales);
    }

    public void delete(Integer id) {
        SalesMapper.delete(id);
    }
}
