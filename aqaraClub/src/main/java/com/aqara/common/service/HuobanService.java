package com.aqara.common.service;

import com.aqara.common.entity.Huoban;
import com.aqara.common.mapper.HuobanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuobanService {
    @Autowired
    HuobanMapper HuobanMapper;

    public void insert(Huoban huoban) {
        HuobanMapper.insert(huoban);
    }

    public List<Huoban> select() {
        return HuobanMapper.select();
    }
}
