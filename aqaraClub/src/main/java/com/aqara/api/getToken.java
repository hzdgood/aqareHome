package com.aqara.api;

import com.aqara.api.common.common;
import com.aqara.api.util.PostUtil;
import com.aqara.api.util.SignUtil;
import java.util.HashMap;
import java.util.Map;

public class getToken {
    static String date = "{\n" +
            "    \"intent\": \"config.auth.getToken\",\n" +
            "    \"data\": {\n" +
            "        \"authCode\": \"9262137771311233\",\n" +
            "        \"accountType\": 1\n" +
            "    }\n" +
            "}";
    public static void main(String[] args) {
        String sign = SignUtil.createSign("", common.appId, common.keyId, common.nonce, common.time, common.appKey);
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json; charset=utf-8");
        map.put("Appid", "application/json; charset=utf-8");
        map.put("Appkey", "application/json; charset=utf-8");
        map.put("Keyid", "application/json; charset=utf-8");
        map.put("Nonce", "application/json; charset=utf-8");
        map.put("Time", "application/json; charset=utf-8");
        map.put("Sign", "application/json; charset=utf-8");
        String dataPost = PostUtil.workRequest(common.url, date, sign, map);
        System.out.println(dataPost);
    }
}
