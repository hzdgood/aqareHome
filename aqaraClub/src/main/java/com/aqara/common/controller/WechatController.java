package com.aqara.common.controller;

import com.alibaba.fastjson.*;
import com.aqara.common.entity.*;
import com.aqara.common.properties.*;
import com.aqara.common.service.*;
import com.aqara.common.utils.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wechat")
public class WechatController {
	@Autowired
	CommonProperties CommonProperties;
	
	@Autowired
	WxProperties WxProperties;

	@Autowired
	WechatService WechatService;
	
	@Autowired
	UserService UserService;

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
		long expired = Long.parseLong(Wechat.getExpired()) * 800; // 有效时间
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
		String res = WeChatUtil.getWxToken(WxProperties, type);
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
	
	/*
	 * 工勘链接企业微信日程接口
	 * */
	@CrossOrigin
	@RequestMapping("/schedule/add")
	public void schedule(Schedule Schedule) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userId = Schedule.getUserid();
		String attendees = "";
		String organizer = "";
		userId = userId.replaceAll(" ", "");
		if(userId.contains("、")) {
			String[] users = userId.split("、");
			for(int i = 0; i < users.length; i++) {
				List<User> userlist = UserService.select(users[i]);
				if(userlist.size() > 0) {
					User User = userlist.get(0);
					attendees += "{" +"	\"userid\": \""+User.getEngName()+"\"" + "},";
				}
			}
		} else {
			List<User> userlist = UserService.select(userId);
			if(userlist.size() > 0) {
				User User = userlist.get(0);
				attendees += "{" +"	\"userid\": \""+User.getEngName()+"\"" + "},";
			}
		}
		List<User> userlist = UserService.selectCode(Schedule.getOrganizer());
		if(userlist.size() > 0 && attendees != "") {
			User User = userlist.get(0);
			organizer = User.getEngName();
			attendees = attendees.substring(0, attendees.length()-1);
		} else {
			return;
		}
		try {
			Date date = sdf.parse(Schedule.getStartTime());
			Long StartTime = date.getTime()/1000;
			int adds = Integer.parseInt(Schedule.getDuration()) * 3600;
			Long endTime = date.getTime()/1000 + adds;
			String token = getToken(CommonProperties.getServiceUrl());
			String userInfo = WxProperties.getScheduleAdd() + "?access_token=" + token;
			String str = "{\"schedule\": {"
				+ "\"organizer\": \""+organizer+"\","
				+ "\"start_time\": " + StartTime + ","
				+ "\"end_time\": " + endTime + ","
				+ "\"summary\": \"" + Schedule.getSummary()+ "\","
				+ "\"description\": \"" + Schedule.getDescription()+ "\","
				+ "\"location\": \"" + Schedule.getLocation()+ "\","
				+ "\"attendees\": [" + attendees + "]}}";
			HttpUtil.scheduleReq(userInfo,str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@CrossOrigin
	@RequestMapping("/calendar/add")
	public void calendarAdd() {
		String token = getToken(CommonProperties.getServiceUrl());
		String userInfo = WxProperties.getCalendarAdd() + "?access_token=" + token;
		System.out.println(token);
		String str = "{"
			+ "\"calendar\" : {"
			+ "\"organizer\" : \"HuangzhaoDong\","
			+ "\"readonly\" : 1,"
			+ "\"set_as_default\" : 1,"
			+ "\"summary\" : \"test_summary\","
			+ "\"color\" : \"#FF3030\","
			+ "\"description\" : \"test_describe\""
			+ "}"
			+ "}";
		JSONObject obj = new JSONObject();
		HttpUtil.dataPost(userInfo, obj.parseObject(str));
	}
}
