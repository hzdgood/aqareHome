package com.aqara.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Coordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class CoordinateUtil {
    private static final String AK = "agKsVR6GPw5eCCzGF5dhnkMoOF9sZGdi"; // 百度地图密钥

    // 调用百度地图API根据地址，获取坐标
    public static Coordinate getCoordinate(String address) {
        Coordinate Coordinate = new Coordinate();
        if (address.equals("") || address == null) {
            return null;
        } else if (!address.contains("上海市")) {
            address = "上海市" + address;
        }
        if (address != null && !"".equals(address)) {
            address = address.substring(2, address.length() - 2);
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + AK;
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.parseObject(json);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    BigDecimal b1 = new BigDecimal(lng);
                    BigDecimal b2 = new BigDecimal(lat);
                    BigDecimal b3 = new BigDecimal(100);
                    BigDecimal b4 = new BigDecimal("12146.31");
                    BigDecimal b5 = new BigDecimal("3118.99");
                    b1 = b1.multiply(b3).subtract(b4);
                    b2 = b2.multiply(b3).subtract(b5);
                    Coordinate.setLng(b1.doubleValue());
                    Coordinate.setLat(b2.doubleValue());
                }
            }
        }
        return Coordinate;
    }

    // 调用百度地图API根据地址，获取坐标
    public static Coordinate Coordinate(String address) {
        Coordinate Coordinate = new Coordinate();
        if (address.equals("") || address == null) {
            return null;
        }
        if (address != null && !"".equals(address)) {
            String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + AK;
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.parseObject(json);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    Coordinate.setLng(lng);
                    Coordinate.setLat(lat);
                }
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
