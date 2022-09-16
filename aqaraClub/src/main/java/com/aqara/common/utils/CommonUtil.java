package com.aqara.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
