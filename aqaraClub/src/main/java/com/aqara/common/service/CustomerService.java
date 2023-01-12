package com.aqara.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Customer;
import com.aqara.common.mapper.CustomerMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    HuobanProperties HuobanProperties;

    public List<Customer> select(String sales, String depart, String startTime, String endTime) {
        return customerMapper.select(sales, depart, startTime, endTime);
    }

    public List<Customer> currentData() {
        return customerMapper.currentData();
    }

    public List<Customer> weekData() {
        return customerMapper.weekData();
    }

    public void insert(Customer customer) {
        customerMapper.insert(customer);
    }

    public void delete(Integer id) {
        customerMapper.delete(id);
    }

    public void getCustomList(String ticket) {
        String str = CommonUtil.getToday();
        String requestUrl = HuobanProperties.getSearchInfo() + "2100000014955786/find";
        JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
        JSONArray array = object.getJSONArray("items");
        for (int i = 0; i < array.size(); i++) {
            Customer Customer = new Customer();
            JSONObject obj = array.getJSONObject(i);
            JSONArray array1 = obj.getJSONArray("fields");
            for (int j = 0; j < array1.size(); j++) {
                JSONObject obj1 = array1.getJSONObject(j);
                String field_id = obj1.getString("field_id");
                JSONArray array2 = obj1.getJSONArray("values");
                JSONObject obj2 = array2.getJSONObject(0);
                if (field_id.equals("2200000144967973")) {
                    Customer.setName(obj2.getString("value"));
                } else if (field_id.equals("2200000144968566")) {
                    Customer.setSales(obj2.getString("title"));
                } else if (field_id.equals("1107001107000000")) {
                    Customer.setDepartment(obj2.getString("title"));
                }
                Customer.setCreateName("上海汇社");
            }
            customerMapper.insert(Customer);
        }
    }

    public String getCurrentData() {
        String str = "**今日CRM新增客户TOP** \n";
        List<Customer> customer = customerMapper.currentData();
        return getDepartInfo(str, customer);
    }

    public String getWeekData() {
        String str = "**上周CRM新增客户TOP** \n";
        List<Customer> customer = customerMapper.weekData();
        return getDepartInfo(str, customer);
    }

    public String getDepartInfo(String str, List<Customer> list) {
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
