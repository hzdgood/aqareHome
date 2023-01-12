package com.aqara.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Collent;
import com.aqara.common.mapper.CollentMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CollentService {

    @Autowired
    CollentMapper collentMapper;

    @Autowired
    HuobanProperties HuobanProperties;

    public List<Collent> select(String code, String startTime, String endTime) {
        return collentMapper.select(code, startTime, endTime);
    }

    public List<Collent> currentData() {
        return collentMapper.currentData();
    }

    public List<Collent> weekData() {
        return collentMapper.weekData();
    }

    public void insert(Collent collent) {
        collentMapper.insert(collent);
    }

    public void delete(Integer id) {
        collentMapper.delete(id);
    }

    public void getCollentList(String ticket) {
        String str = CommonUtil.getToday();
        String requestUrl = HuobanProperties.getSearchInfo() + "2100000015000019/find";
        JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
        JSONArray array = object.getJSONArray("items");
        for (int i = 0; i < array.size(); i++) {
            Collent Collent = new Collent();
            JSONObject obj = array.getJSONObject(i);
            JSONArray array1 = obj.getJSONArray("fields");
            for (int j = 0; j < array1.size(); j++) {
                JSONObject obj1 = array1.getJSONObject(j);
                String field_id = obj1.getString("field_id");
                JSONArray array2 = obj1.getJSONArray("values");
                JSONObject obj2 = array2.getJSONObject(0);
                if (field_id.equals("2200000145309762")) {
                    Collent.setCode(obj2.getString("title"));
                } else if (field_id.equals("1101001117000000")) {
                    Collent.setSales(obj2.getString("title"));
                } else if (field_id.equals("1101001117001107")) {
                    Collent.setDepartment(obj2.getString("title"));
                } else if (field_id.equals("2200000145309763")) {
                    Collent.setCollectionStatus(obj2.getString("name"));
                } else if (field_id.equals("2200000145309764")) {
                    Collent.setPaymentMethod(obj2.getString("name"));
                }
                Collent.setCreateName("上海汇社");
            }
            collentMapper.insert(Collent);
        }
    }

    public String getCollentData() {
        String str = "**今日CRM新增全款TOP** \n";
        List<Collent> collent = collentMapper.currentData();
        return getDepartInfo(str, collent);
    }

    public String getWeekData() {
        String str = "**上周CRM新增全款TOP** \n";
        List<Collent> collent = collentMapper.weekData();
        return getDepartInfo(str, collent);
    }

    public String getDepartInfo(String str, List<Collent> list) {
        if (list.size() == 0) {
            return "";
        }
        Map<String, Integer> map = new HashMap<>();
        list.forEach(name -> {
            Integer counts = map.get(name.getSales());
            map.put(name.getSales(), counts == null ? 1 : ++counts);
        });
        Map<String, Integer> map1 = CommonUtil.sortByValue(map);
        Iterator entries = map1.entrySet().iterator();
        int index = 0;
        while (entries.hasNext()) {
            if (index >= 3) {
                break;
            }
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();
            str += ">" + key + ":<font color=\"comment\">" + value + "</font>\n";
            index += 1;
        }
        return str;
    }
}
