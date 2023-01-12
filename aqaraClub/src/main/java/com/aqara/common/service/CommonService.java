package com.aqara.common.service;

import com.aqara.common.entity.Common;
import com.aqara.common.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
