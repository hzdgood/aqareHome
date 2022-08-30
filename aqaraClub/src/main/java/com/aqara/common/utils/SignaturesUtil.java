package com.aqara.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.properties.WxProperties;

public class SignaturesUtil {
	public static JSONObject signatures(String url, String token, WxProperties WxProperties) {
		String nonceStr = RandomString.getRandomString(16);
		String timeNew = System.currentTimeMillis() + "";
		String jsapi_ticket = WeChatUtil.getJsApiTicket(WxProperties, token);
		String appticket = WeChatUtil.getAppTicket(WxProperties, token);
		JSONObject jsonObject = JSONObject.parseObject(jsapi_ticket);
		String ticket = jsonObject.getString("ticket");
		jsonObject = JSONObject.parseObject(appticket);
		String LastAppticket = jsonObject.getString("ticket");
		String str = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timeNew + "&url=" + url;
		String sign = DigestUtils.shaHex(str);
		String str1 = "jsapi_ticket=" + LastAppticket + "&noncestr=" + nonceStr + "&timestamp=" + timeNew + "&url=" + url;
		String sign1 = DigestUtils.shaHex(str1);
		JSONObject meta = new JSONObject();
		meta.put("nonceStr", nonceStr);
		meta.put("timestamp", timeNew);
		meta.put("url", url);
		JSONObject signature = new JSONObject();
		signature.put("signature", sign);
		JSONObject appTicket = new JSONObject();
		appTicket.put("signature", sign1);
		JSONObject resObj = new JSONObject();
		resObj.put("meta", meta);
		resObj.put("corp", signature);
		resObj.put("app", appTicket);
		return resObj;
	}
}
