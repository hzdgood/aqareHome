package com.aqara.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CommonUtil {
	public static JSONObject getDataJson() {
		JSONObject o1 = new JSONObject();
		o1.put("eq", "today");
		JSONObject o2 = new JSONObject();
		o2.put("field", "created_on");
		o2.put("query", o1);
		JSONArray array = new JSONArray();
		array.add(o2);
		JSONObject o3 = new JSONObject();
		o3.put("and", array);
		JSONObject obj = new JSONObject();
		obj.put("where", o3);
		obj.put("offset", 0);
		obj.put("limit", 50);
		return obj;
	}
	
	public static String getToday() {
		String str = "{\"where\":{\"and\":[{\"field\":\"created_on\",\"query\":{\"eq\":\"today\"}}]},\"offset\":0,\"limit\":20}";
		return str;
	}
	
	public static String getSurveyTime() {
		String str = "{\"where\":{\"and\":[{\"field\":2200000168338254,\"query\":{\"eq\":\"today\"}}]},\"offset\":0,\"limit\":20,"
				+ "\"order_by\":[{\"field\":2200000169723711,\"sort\":\"asc\"}]}";
		return str;
	}
	
	public static String  getCurtainData() {
		String str = "{\"where\":{\"and\":[{\"field\":2200000159767482,"
				+ "\"query\":{\"in\":[1,2]}},{\"field\":2200000159767485,"
				+ "\"query\":{\"em\":true}}]},\"offset\":0,\"limit\":200}";
		return str;
	}
	
}
