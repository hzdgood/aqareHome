package com.aqara.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.properties.QyProperties;

public class QyUtil {
    public static String getProviderToken(QyProperties qyProperties, String type) {
        String corpId = qyProperties.getCorpID();
        String provider_secret = qyProperties.getProviderSecret();
        String url = qyProperties.getProvider_token();
        String result = "";
        try {
            JSONObject obj = new JSONObject();
            obj.put("corpid", corpId);
            obj.put("provider_secret", provider_secret);
            result = HttpUtil.dataPost(url, obj);
        } catch (Exception e) {
            System.out.print("获取token getWxToken");
        }
        return result;
    }
}
