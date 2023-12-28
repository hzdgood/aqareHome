package com.aqara.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

public class JsonUtil {
    public static Object findValueByKey(JSONObject json, String key) {
        Set<String> keySet = json.keySet();
        for (String k : keySet) {
            Object v = json.get(k);
            if (k.equals(key)) {
                return v;
            } else if (v instanceof JSONArray) {
                int size = ((JSONArray) v).size();
                for (int i = 0; i <= size - 1; i++) {
                    Object result = findValueByKey((JSONObject) ((JSONArray) v).get(i), key);
                    if (result != null) {
                        return result;
                    }
                }
            } else if (v instanceof JSONObject) {
                Object result = findValueByKey((JSONObject) v, key);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
