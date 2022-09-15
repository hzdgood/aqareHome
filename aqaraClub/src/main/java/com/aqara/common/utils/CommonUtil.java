package com.aqara.common.utils;

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
	
	public static String  getCurtainData() {
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
		String str = "{\"where\":{\"and\":[{\"field\":\"created_on\","
				+ "\"query\":{\"eq\":\"today\"}}]},\"offset\":0,\"limit\":20}";
		return str;
	}
	
}
