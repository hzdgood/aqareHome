package com.aqara.common.service;

import com.aqara.common.entity.RollerShutter;
import com.aqara.common.mapper.RollerShutterMapper;
import com.aqara.common.properties.HuobanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerShutterService {

    @Autowired
    RollerShutterMapper RollerShutterMapper;

    @Autowired
    HuobanProperties HuobanProperties;

    public List<RollerShutter> currentData() {
        return RollerShutterMapper.currentData();
    }

    public List<RollerShutter> currentData1() {
        return RollerShutterMapper.currentData1();
    }

    public void insert(RollerShutter RollerShutter) {
        RollerShutterMapper.insert(RollerShutter);
    }

    public void upload(RollerShutter RollerShutter) {
        RollerShutterMapper.upload(RollerShutter);
    }

    public void delete(Integer id) {
        RollerShutterMapper.delete(id);
    }
}
