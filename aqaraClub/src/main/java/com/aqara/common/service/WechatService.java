package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.Wechat;
import com.aqara.common.mapper.WechatMapper;

@Service
public class WechatService {
	@Autowired
	WechatMapper WechatMapper;
	
	public void insert(Wechat Wechat) {
		WechatMapper.insert(Wechat);
	}
	
	public List<Wechat> select(String type) {
		return WechatMapper.select(type);
	}
}
