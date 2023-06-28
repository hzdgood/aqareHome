package com.aqara.api;

import io.micrometer.common.util.StringUtils;
import java.security.MessageDigest;
import java.util.Date;

public class CreateSign {
    public static void main(String[] args) {
        String accessToken = "532cad73c5493193d63d367016b98b27";
        String appId = "1122919936706088960ff200";
        String keyId = "K.1122919936748032000";
        String nonce = String.valueOf(new Date().getTime());
        String time = String.valueOf(new Date().getTime());
        String appKey = "0une4ix8nmxzkoe1sop2ds6ynxy2x7an";

        String sign = createSign(accessToken, appId, keyId, nonce, time, appKey);
        System.out.println(sign);
    }

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
            return MD5_32(signStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String MD5_32(String sourceStr) throws Exception {
        String result = "";
        try {
            byte[] b = md5(sourceStr.getBytes("UTF-8"));
            StringBuffer buf = new StringBuffer("");
            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        return result;
    }

    private static byte[] md5(byte[] bytes) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        return md.digest();
    }
}
