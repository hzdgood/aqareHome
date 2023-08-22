package com.yunqi.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    public static String dataPost(String requestUrl, JSONObject obj) {
        try {
            if (requestUrl == null || requestUrl.equals("")) {
                return null;
            }
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(obj.toString());
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
                sb.append(lines);
            }
            reader.close();
            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("dataPost" + requestUrl);
            return "";
        }
    }

    public static void workRequest(String temp, String WX_TOKEN) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(WX_TOKEN);
            httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
            Map<String, Object> param = new HashMap<String, Object>();
            Map<String, String> map = new HashMap<String, String>();
            map.put("content", temp);
            param.put("msgtype", "markdown");
            param.put("markdown", map);
            String p = JSON.toJSONString(param);
            StringEntity entity = new StringEntity(p, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("workRequest" + WX_TOKEN);
        }
    }

//    public static String mediaPost(String requestUrl, String filename) throws IOException {
//        String boundary = UUID.randomUUID().toString().replace("-", "");
//        HttpPost post = new HttpPost(requestUrl);
//        HttpClient client = HttpClients.createDefault();
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.setMode(HttpMultipartMode.RFC6532);
//        File file = new File(filename);
//        if (file != null && file.exists()) {
//            builder.addBinaryBody("file", file, ContentType.parse(boundary), file.getName());
//        }
//        post.setEntity(builder.build());
//        HttpResponse response = client.execute(post);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8), 8 * 1024);
//        String lines;
//        StringBuffer sb = new StringBuffer();
//        while ((lines = reader.readLine()) != null) {
//            lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
//            sb.append(lines);
//        }
//        return sb.toString();
//    }
//
//    public static String uploadImg(String filename, String accessToken) {
//        RestTemplate restTemplate = new RestTemplate();
//        URI uri = UriComponentsBuilder.fromHttpUrl("https://qyapi.weixin.qq.com/cgi-bin/media/upload")
//                .queryParam("access_token", accessToken)
//                .queryParam("type", "image")
//                .build().toUri();
//        FileSystemResource fileSystemResource = new FileSystemResource(filename);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        ContentDisposition build = ContentDisposition.builder("form-data").filename(fileSystemResource.getFilename()).build();
//        headers.setContentDisposition(build);
//        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//        params.add("media", fileSystemResource);
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//        String s = restTemplate.postForObject(uri, requestEntity, String.class);
//        return s;
//    }
//
//
//    public static String get(String requestUrl) {
//        try {
//            if (requestUrl == null || requestUrl.equals("")) {
//                return null;
//            }
//            URL url = new URL(requestUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("GET");
//            connection.setUseCaches(false);
//            connection.setInstanceFollowRedirects(true);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.connect();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String lines;
//            StringBuffer sb = new StringBuffer();
//            while ((lines = reader.readLine()) != null) {
//                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
//                sb.append(lines);
//            }
//            reader.close();
//            connection.disconnect();
//            return sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.printf("get：" + requestUrl);
//            return "";
//        }
//    }
//
//    public static String post(String requestUrl) {
//        try {
//            if (requestUrl == null || requestUrl.equals("")) {
//                return null;
//            }
//            URL url = new URL(requestUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("POST");
//            connection.setUseCaches(false);
//            connection.setInstanceFollowRedirects(true);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.connect();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String lines;
//            StringBuffer sb = new StringBuffer();
//            while ((lines = reader.readLine()) != null) {
//                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
//                sb.append(lines);
//            }
//            reader.close();
//            connection.disconnect();
//            return sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.printf("post:" + requestUrl);
//            return "";
//        }
//    }
//    public static void scheduleReq(String requestUrl, String obj) {
//        try {
////            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(requestUrl);
//            httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
//            StringEntity entity = new StringEntity(obj, "utf-8");
//            httpPost.setEntity(entity);
////            HttpResponse response = httpClient.execute(httpPost);
////            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
////                String res =  EntityUtils.toString(response.getEntity(), "UTF-8");
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
