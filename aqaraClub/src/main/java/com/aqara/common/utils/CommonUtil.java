package com.aqara.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.properties.WxProperties;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {
    public static String getUserJson() {
        return "{\"where\":{\"and\":[{\"field\":2200000257456339,\"query\":{\"em\":false}}]},"
                + "\"offset\":0,\"limit\":100,\"order_by\":[{}]}";
    }

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

    public static String getWorkData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = new Date();
        String today = simpleDateFormat.format(data);
        String tom = simpleDateFormat.format(dateAddOne(data));
        String str = "{\"where\":{\"and\":[{\"field\":2200000145748099," +
                "\"query\":{\"range\":[{\"model\":\"static\",\"datetime\":\"" + today + "\"}," +
                "{\"model\":\"static\",\"datetime\":\"" + tom + "\"}]}}," +
                "{\"field\":2200000145748100,\"query\":{\"em\":false}}," +
                "{\"field\":2200000146473059,\"query\":{\"in\":[1]}}]},\"offset\":0,\"limit\":20,\"order_by\":" +
                "[{\"field\":2200000146199958,\"sort\":\"desc\"}]}";
        return str;
    }

    public static String getNoCompleteWorkData() {
        String str = "{\"where\":{\"and\":[{\"field\":2200000145748099," +
                "\"query\":{\"em\":true}},{\"field\":2200000145748100," + // 上门技术
                "\"query\":{\"em\":false}},{\"field\":\"created_on\"," + // 上门日期
                "\"query\":{\"eq\":\"this_year\"}},{\"field\":2200000146199958," + // 今年
                "\"query\":{\"em\":false}},{\"field\":2200000146398522," + // 完成时间
                "\"query\":{\"em\":true}},{\"field\":2200000146473059," + // 订单日期
                "\"query\":{\"in\":[1]}}]},\"offset\":0,\"limit\":100," + // 有效
                "\"order_by\":[{\"field\":2200000146199958,\"sort\":\"desc\"}]}";
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
                + "{\"query\":{\"or\":[{\"gte\":\"1\"}]},\"query_option_mappings\":[-1],\"field\":2200000150080669}" // 总价
                // + "{\"field\":2200000154568276,\"query\":{\"em\":true,\"in\":[7,6,5,4,3,2,1]}}" // 装修进度
                + " ]},"
                + "\"offset\":0,\"limit\":100,\"order_by\":"
                + "[{\"field\":2200000149037697,\"sort\":\"desc\"}]}";
        return str;
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
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
        }.reversed());
        // 将排序后的元素放到LinkedHashMap中
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static Date dateAddOne(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    public static JSONObject signatures(String url, String token, WxProperties WxProperties) {
        String nonceStr = CommonUtil.getRandomString(16);
        String timeNew = String.valueOf(System.currentTimeMillis());
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
