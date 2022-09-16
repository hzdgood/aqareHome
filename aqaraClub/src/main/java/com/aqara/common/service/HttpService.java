package com.aqara.common.service;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.utils.HttpUtil;
import com.aqara.common.utils.HuobanUtil;

@Service
public class HttpService {

	@Retryable(value = { Exception.class }, maxAttempts = 3)
	public static JSONObject getSchedule(String requestUrl, String ticket, JSONObject obj) {
		try {
			return HuobanUtil.getSchedule(requestUrl, ticket, obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Recover
	public void recover(RemoteAccessException e) {
		System.out.println(e.getMessage());
	}

	public void workRequset(String str, String WX_TOKEN) {
		HttpUtil.workRequset(str, WX_TOKEN);
	}
}
