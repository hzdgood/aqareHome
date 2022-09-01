package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqara.common.mapper.CustomerMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	HuobanProperties HuobanProperties;
	
	public List<Customer> select(String sales, String depart, String startTime, String endTime){
		return customerMapper.select(sales, depart ,startTime, endTime);
	}
	
	public List<Customer> currentData(){
		return customerMapper.currentData();
	}
	
	public void insert(Customer customer) {
		customerMapper.insert(customer);
	}
	
	public void delete(Integer id) {
		customerMapper.delete(id);
	}
	
	public void upload(Customer customer) {
		customerMapper.upload(customer);
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
	
}
