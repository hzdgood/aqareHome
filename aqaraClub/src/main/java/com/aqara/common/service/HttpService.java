package com.aqara.common.service;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.utils.HttpUtil;
import com.aqara.common.utils.HuobanUtil;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class HttpService {
    @Retryable(value = {Exception.class}, maxAttempts = 3)
    public static JSONObject getSchedule(String requestUrl, String ticket, JSONObject obj) {
        try {
            return HuobanUtil.getSchedule(requestUrl, ticket, obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void workRequset(String str, String WX_TOKEN) {
        HttpUtil.workRequest(str, WX_TOKEN);
    }
}
