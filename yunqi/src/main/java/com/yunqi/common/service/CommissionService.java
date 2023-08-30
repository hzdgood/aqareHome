package com.yunqi.common.service;

import com.yunqi.common.entity.Commission;
import com.yunqi.common.mapper.CommissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommissionService {
    private CommissionMapper CommissionMapper;

    @Autowired
    public void setMapper(CommissionMapper CommissionMapper) {
        this.CommissionMapper = CommissionMapper;
    }

    public List<Commission> select() {
        return CommissionMapper.select();
    }

    public void insert(Commission Commission) {
        CommissionMapper.insert(Commission);
    }

    public void update(Commission Commission) {
        CommissionMapper.update(Commission);
    }

    public void delete(Integer id) {
        CommissionMapper.delete(id);
    }
}
