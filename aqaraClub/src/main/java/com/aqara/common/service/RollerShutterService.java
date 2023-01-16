package com.aqara.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.RollerShutter;
import com.aqara.common.mapper.RollerShutterMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public void delete(Integer id) { RollerShutterMapper.delete(id); }
}
