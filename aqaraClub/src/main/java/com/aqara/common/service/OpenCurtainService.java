package com.aqara.common.service;

import com.aqara.common.entity.OpenCurtain;
import com.aqara.common.mapper.OpenCurtainMapper;
import com.aqara.common.properties.HuobanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenCurtainService {

    @Autowired
    OpenCurtainMapper OpenCurtainMapper;

    @Autowired
    HuobanProperties HuobanProperties;

    public List<OpenCurtain> currentData() {
        return OpenCurtainMapper.currentData();
    }

    public List<OpenCurtain> currentData1() {
        return OpenCurtainMapper.currentData1();
    }

    public void insert(OpenCurtain OpenCurtain) {
        OpenCurtainMapper.insert(OpenCurtain);
    }

    public void upload(OpenCurtain OpenCurtain) {
        OpenCurtainMapper.upload(OpenCurtain);
    }

    public void delete(Integer id) {
        OpenCurtainMapper.delete(id);
    }
}
