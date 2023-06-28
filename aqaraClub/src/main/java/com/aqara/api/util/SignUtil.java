package com.aqara.api.util;

import io.micrometer.common.util.StringUtils;

public class SignUtil {

    public static String createSign(String accessToken, String appId, String keyId, String nonce, String time, String appKey) {
        // 严格按照Accesstoken、Appid、Keyid、Nonce、Time
        // 顺序拼接为一个string串使用MD5生成签名值，将生成的签名值放在RequestHeader的Sign中；
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(accessToken)){
            sb.append("Accesstoken=").append(accessToken).append("&");
        }
        sb.append("Appid=").append(appId);
        sb.append("&").append("Keyid=").append(keyId);
        sb.append("&").append("Nonce=").append(nonce);
        sb.append("&").append("Time=").append(time).append(appKey);
        String signStr = sb.toString().toLowerCase();
        try {
            return Md5Util.encryption(signStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
