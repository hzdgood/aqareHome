package com.aqara.common.service;

import com.aqara.common.entity.Wechat;
import com.aqara.common.mapper.WechatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatService {
    private WechatMapper WechatMapper;

    @Autowired
    public void setMapper(WechatMapper WechatMapper) {
        this.WechatMapper = WechatMapper;
    }

    public void insert(Wechat Wechat) {
        WechatMapper.insert(Wechat);
    }

    public List<Wechat> select(String type) {
        return WechatMapper.select(type);
    }
}
