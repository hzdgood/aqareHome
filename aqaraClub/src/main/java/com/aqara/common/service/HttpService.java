package com.aqara.common.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.utils.HuobanUtil;

@Service
public class HttpService {
	
	@Retryable(recover = "getUrl")
	public static JSONObject getSchedule(String requestUrl, String ticket, JSONObject obj) {
		return HuobanUtil.getSchedule(requestUrl, ticket, obj);
	}
	
	@Recover
	public static JSONObject getUrl(String requestUrl, String ticket, JSONObject obj) {
		return HuobanUtil.getSchedule(requestUrl, ticket, obj);
	}
}
