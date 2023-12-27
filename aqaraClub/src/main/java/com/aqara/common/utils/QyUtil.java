package com.aqara.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import com.aqara.common.properties.QyProperties;

public class QyUtil {
    public static String JsSignatures(String url, String token, QyProperties QyProperties) {
        String nonceStr = CommonUtil.getRandomString(16);
        String timeNew = String.valueOf(System.currentTimeMillis());
        String jsapi_ticket = getJsApiTicket(QyProperties, token);
        return getString(url, nonceStr, timeNew, jsapi_ticket);
    }

    public static String AppSignatures(String url, String token, QyProperties QyProperties) {
        String nonceStr = CommonUtil.getRandomString(16);
        String timeNew = String.valueOf(System.currentTimeMillis());
        String appTicket = getAppTicket(QyProperties, token);
        return getString(url, nonceStr, timeNew, appTicket);
    }

    private static String getString(String url, String nonceStr, String timeNew, String appTicket) {
        JSONObject jsonObject = JSONObject.parseObject(appTicket);
        String ticket = null;
        if (jsonObject != null) {
            ticket = jsonObject.getString("ticket");
        }
        String str = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timeNew + "&url=" + url;
        return DigestUtils.shaHex(str);
    }


    public static String getJsApiTicket(QyProperties QyProperties, String access_token) {
        String url = QyProperties.getJsapiTicket(); // 获取企业 jsapi_ticket
        if (access_token.isEmpty()) {
            return null;
        }
        String lastUrl = url + "?access_token=" + access_token;
        String result = "";
        try {
            result = HttpUtil.post(lastUrl);
        } catch (Exception e) {
            System.out.print("获取token getJsApiTicket");
        }
        return result;
    }

    public static String getAppTicket(QyProperties QyProperties, String token) {
        String url = QyProperties.getAppTicket(); // 获取应用 jsapi_ticket
        if (token.isEmpty()) {
            return null;
        }
        String lastUrl = url + "?access_token=" + token + "&type=agent_config";
        String result = "";
        try {
            result = HttpUtil.post(lastUrl);
        } catch (Exception e) {
            System.out.print("获取token getAppTicket");
        }
        return result;
    }
}
