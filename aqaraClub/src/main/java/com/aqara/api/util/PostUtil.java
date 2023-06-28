package com.aqara.api.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PostUtil {

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
    static String nonce = RandomStrUtil.getRandomString(16);
    static String time = String.valueOf(System.currentTimeMillis());
    static String appKey = "0une4ix8nmxzkoe1sop2ds6ynxy2x7an";
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
}
