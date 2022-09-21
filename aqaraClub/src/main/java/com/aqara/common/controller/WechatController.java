package com.aqara.common.controller;

import com.alibaba.fastjson.*;
import com.aqara.common.entity.*;
import com.aqara.common.properties.WxProperties;
import com.aqara.common.service.*;
import com.aqara.common.utils.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wechat")
public class WechatController {
	@Autowired
	WxProperties WxProperties;

	@Autowired
	WechatService WechatService;

	/**
	 * 获取企业微信token
	 */
	@RequestMapping("/getToken")
	public String getToken(@RequestBody String type) {
		String token = "";
		List<Wechat> list = WechatService.select(type);
		if(list.size() == 0) {
			return insert(type);
		}
		Wechat Wechat = list.get(list.size() - 1);
		long expired = Long.parseLong(Wechat.getExpired()) * 1000; // 有效时间
		long date = Wechat.getDate().getTime(); // 凭证开始时间
		long sys = System.currentTimeMillis(); // 当前
		if (expired + date <= sys) { //是否过期
			return insert(type);
		} else {
			token = Wechat.getTicket();
		}
		return token;
	}
	
	synchronized private String insert(String type) {
		String res = WeChatUtil.getWxToken(WxProperties);
		JSONObject jsonObject = JSONObject.parseObject(res);
		Wechat Wechat = new Wechat();
		Wechat.setTicket(jsonObject.getString("access_token"));
		Wechat.setType(type);
		Wechat.setExpired(jsonObject.getString("expires_in"));
		WechatService.insert(Wechat);
		return jsonObject.getString("access_token");
	}

	/**
	 * 获取当前用户
	 */
	@CrossOrigin
	@RequestMapping("/getUserId")
	public String getUserId(String code, String type) {
		String token = getToken(type);
		String res = WeChatUtil.getUserId(WxProperties, token, code);
		JSONObject jsonObject = JSONObject.parseObject(res);
		String UserId = jsonObject.getString("UserId");
		return UserId;
	}

	/**
	 * JSJSD注册
	 */
	@CrossOrigin
	@RequestMapping("/signatures")
	public JSONObject signatures(String url, String type) {
		String token = getToken(type);
		JSONObject jsonObject = CommonUtil.signatures(url, token, WxProperties);
		return jsonObject;
	}

	/**
	 * 获取外部联系人详细信息
	 */
	@CrossOrigin
	@RequestMapping("/externalcontact")
	public String externalcontact(String userId, String type) {
		String token = getToken(type);
		String res = WeChatUtil.getExternalContact(userId, token, WxProperties);
		return res;
	}
	
	/**
	 * 获取外部联系人详细信息
	 */
	@CrossOrigin
	@RequestMapping("/externalList")
	public String externalList(String userId, String type) {
		String token = getToken(type);
		String res = WeChatUtil.externalList(userId, token, WxProperties);
		return res;
	}
	
	/**
	 * 获取外部联系人详细信息
	 */
	@CrossOrigin
	@RequestMapping("/groupchat")
	public String groupchat(String chatId, String type) {
		String token = getToken(type);
		String userInfo = WxProperties.getGroupchat() + "?access_token=" + token;
		JSONObject obj = new JSONObject();
		obj.put("chat_id", chatId);
		String res = HttpUtil.dataPost(userInfo, obj);
		return res;
	}
	
	/**
	 * 获取外部联系人详细信息
	 */
	@CrossOrigin
	@RequestMapping("/grouplist")
	public String grouplist(String chatId, String type) {
		String token = getToken(type);
		String userInfo = WxProperties.getGroupList() + "?access_token=" + token;
		JSONObject obj = new JSONObject();
		obj.put("chat_id", chatId);
		String res = HttpUtil.dataPost(userInfo, obj);
		return res;
	}
	
	@CrossOrigin
	@RequestMapping("/calendarAdd")
	public void calendarAdd(Calendar Calendar) {
		String token = getToken("http://localhost:8081");
		String userInfo = WxProperties.getCalendarAdd() + "?access_token=" + token;
		
		
		System.out.println(Calendar);
	}
	
}
