package com.aqara.api;

import com.aqara.api.common.common;
import com.aqara.api.util.PostUtil;
import com.aqara.api.util.SignUtil;
import java.util.HashMap;
import java.util.Map;

public class getToken {
    static String url = "https://open-cn.aqara.com/v3.0/open/api";
    static String date = "{\n" +
            "  \"intent\": \"query.device.info\",\n" +
            "  \"data\": {\n" +
            "    \"positionId\": \"\",\n" +
            "    \"pageNum\": 1,\n" +
            "    \"pageSize\": 50\n" +
            "  }\n" +
            "}";
    static String token = "9150d91bedf37c78800aa3ce7a831770";

    public static void main(String[] args) {
        String sign = SignUtil.createSign(token, common.appId, common.keyId, common.nonce, common.time, common.appKey);
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json; charset=utf-8");
        map.put("Accesstoken", token);
        map.put("Appid", common.appId);
        map.put("Keyid", common.keyId);
        map.put("Nonce", common.nonce);
        map.put("Time", common.time);
        map.put("Sign", sign);
        String dataPost = PostUtil.workRequest(url, date, map);
        System.out.println(dataPost);
    }
}
