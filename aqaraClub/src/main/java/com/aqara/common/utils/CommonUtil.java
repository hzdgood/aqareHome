package com.aqara.common.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.properties.WxProperties;

public class CommonUtil {
	public static String getToday() {
		String str = "{\"where\":{\"and\":[{\"field\":\"created_on\","
				+ "\"query\":{\"eq\":\"today\"}}]},\"offset\":0,\"limit\":20}";
		return str;
	}

	public static String getSurveyTime() {
		String str = "{\"where\":{\"and\":[{\"field\":2200000168338254,"
				+ "\"query\":{\"eq\":\"today\"}}]},\"offset\":0,\"limit\":20,"
				+ "\"order_by\":[{\"field\":2200000169723711,\"sort\":\"asc\"}]}";
		return str;
	}

	public static String getCurtainData() {
		String str = "{\"where\":{\"and\":[{\"field\":2200000159767482,"
				+ "\"query\":{\"in\":[1,2]}},{\"field\":2200000159767485,"
				+ "\"query\":{\"em\":true}}]},\"offset\":0,\"limit\":200}";
		return str;
	}

	public static String getShutterData() {
		String str = "{\"where\":{\"and\":[{\"field\":2200000159475099,"
				+ "\"query\":{\"em\":true}},{\"field\":2200000159765503,"
				+ "\"query\":{\"in\":[1,2]}}]},\"offset\":0,\"limit\":200}";
		return str;
	}

	public static String getProjectData() {
		Date date = new Date();
		date.setMonth(date.getMonth() - 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String serData = simpleDateFormat.format(date);
		String str = "{\"where\":{\"and\":["
				+ "{\"field\":\"updated_on\",\"query\":{\"range\":[null,{\"model\":\"static\",\"datetime\":\"" + serData + "\"}]}}," // 修改日期
				+ "{\"field\":2200000147975001,\"query\":{\"em\":true,\"in\":[2]}}," // 是否完结
				+ "{\"field\":2200000148980437,\"query\":{\"in\":[1]}}," // 类型
				+ "{\"query\":{\"or\":[{\"gte\":\"1\"}]},\"query_option_mappings\":[-1],\"field\":2200000150080669}," // 总价
				+ "{\"field\":2200000154568276,\"query\":{\"em\":true,\"in\":[7,6,5,4,3,2,1]}}]},\"offset\":0,\"limit\":100,\"order_by\":" // 装修进度
				+ "[{\"field\":2200000149037697,\"sort\":\"desc\"}]}";
		return str;
	}
	
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	
	public static HashMap<String, Integer> sortByValue(Map<String, Integer> map) {
		// HashMap的entry放到List中
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		// 对List按entry的value排序
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		}.reversed() );
		// 将排序后的元素放到LinkedHashMap中
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}
	
	public static JSONObject signatures(String url, String token, WxProperties WxProperties) {
		String nonceStr = CommonUtil.getRandomString(16);
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
