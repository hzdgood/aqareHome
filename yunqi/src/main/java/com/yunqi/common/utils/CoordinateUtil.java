package com.yunqi.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.yunqi.common.entity.Coordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class CoordinateUtil {
    private static final String AK = "agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi"; // 百度地图密钥

    // 调用百度地图API根据地址，获取坐标
    public static Coordinate Coordinate(String address) {
        Coordinate Coordinate = new Coordinate();
        if (address.isEmpty()) {
            return null;
        }
        address = address.replaceAll(" ", "");
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + AK;
        String json = loadJSON(url);
        if (!json.isEmpty()) {
            JSONObject obj = JSONObject.parseObject(json);
            if ("0".equals(obj.getString("status"))) {
                double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                Coordinate.setLng(lng);
                Coordinate.setLat(lat);
            }
        }
        return Coordinate;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), StandardCharsets.UTF_8));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            System.out.printf(json + "--------" + "loadJSON");
            e.printStackTrace();
        }
        return json.toString();
    }
}