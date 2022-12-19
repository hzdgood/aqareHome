package com.aqara.common.service;

import com.aqara.common.entity.Common;
import com.aqara.common.mapper.CommonMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    CommonMapper CommonMapper;

    public List<Common> getValue(String name) {
        return CommonMapper.getValue(name);
    }

    public void insert(Common Common) {
        CommonMapper.insert(Common);
    }
}
