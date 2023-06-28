package com.aqara.api;

import io.micrometer.common.util.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

public class getToken {
    static String url = "https://open-cn.aqara.com/v3.0/open/api";
    static String date = "{\n" +
            "    \"intent\": \"config.auth.getToken\",\n" +
            "    \"data\": {\n" +
            "        \"authCode\": \"9262137771311233\",\n" +
            "        \"accountType\": 1\n" +
            "    }\n" +
            "}";
    static String appId = "1122919936706088960ff200";
    static String keyId = "K.1122919936748032000";
    static String nonce = getRandomString(16);
    static String time = String.valueOf(System.currentTimeMillis());
    static String appKey = "0une4ix8nmxzkoe1sop2ds6ynxy2x7an";

    public static void main(String[] args) {
        String sign = createSign("", appId, keyId, nonce, time, appKey);
        String dataPost = workRequest(url, date, sign);
        System.out.println(dataPost);
    }

    public static String workRequest(String url, String date, String Sign) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
            httpPost.addHeader("Appid",appId);
            httpPost.addHeader("Appkey",appKey);
            httpPost.addHeader("Keyid",keyId);
            httpPost.addHeader("Nonce",nonce);
            httpPost.addHeader("Time",time);
            httpPost.addHeader("Sign",Sign);
            StringEntity entity = new StringEntity(date, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), 8 * 1024);
            String lines;
            StringBuffer sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
                sb.append(lines);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("workRequest" + url);
        }
        return "";
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
            return encryption(signStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRandomString(int length) {
        String str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String encryption(String plainText) {
        String md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            md5 = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }
}
