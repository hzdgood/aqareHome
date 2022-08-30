package com.aqara.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Huoban;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.service.HuobanService;
import com.aqara.common.utils.HttpUtil;

@RestController
@RequestMapping("/huoban")
public class HuobanController {
	@Autowired
	HuobanProperties huobanProperties;

	@Autowired
	HuobanService huobanService;

	@CrossOrigin
	@RequestMapping("/getTicket")
	public String getTicket() {
		String result = "";
		List<Huoban> list = huobanService.select();
		if(list.size() == 0) {
			return insert();
		}
		Huoban Huoban = list.get(list.size() - 1); //获取最后一条数据
		long expired = Long.parseLong(Huoban.getExpired()) * 900; // 有效时间
		long date = Huoban.getDate().getTime(); // 凭证开始时间
		long sys = System.currentTimeMillis(); // 当前
		if (expired + date <= sys) { //是否过期
			return insert();
		} else {
			JSONObject json = new JSONObject();
			json.put("ticket", Huoban.getTicket());
			result = json.toString();
		}
		return result; // 后期处理到数据库
	}
	
	synchronized private String insert() {
		String application_id = huobanProperties.getApplication_id();
		String application_secret = huobanProperties.getApplication_secret();
		String userInfo = huobanProperties.getUserInfo();
		JSONObject obj = new JSONObject();
		obj.put("application_id", application_id);
		obj.put("application_secret", application_secret);
		String result = HttpUtil.dataPost(userInfo, obj);
		JSONObject jsonObject = JSONObject.parseObject(result);
		Huoban huoban = new Huoban();
		huoban.setTicket(jsonObject.getString("ticket"));
		huoban.setExpired(jsonObject.getString("expired"));
		huobanService.insert(huoban);
		return result;
	}
}
