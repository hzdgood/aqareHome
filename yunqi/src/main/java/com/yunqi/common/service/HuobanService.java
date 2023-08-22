package com.yunqi.common.service;

import com.yunqi.common.entity.Huoban;
import com.yunqi.common.mapper.HuobanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuobanService {
    private HuobanMapper HuobanMapper;

    @Autowired
    public void setMapper(HuobanMapper HuobanMapper) {
        this.HuobanMapper = HuobanMapper;
    }

    public void insert(Huoban huoban) {
        HuobanMapper.insert(huoban);
    }

    public List<Huoban> select() {
        return HuobanMapper.select();
    }
}
