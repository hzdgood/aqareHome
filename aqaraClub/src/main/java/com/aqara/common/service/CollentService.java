package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Collent;
import com.aqara.common.mapper.CollentMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;

@Service
public class CollentService {
	
	@Autowired
	CollentMapper collentMapper;
	
	@Autowired
	HuobanProperties HuobanProperties;
	
	public List<Collent> select(String code, String startTime, String endTime){
		return collentMapper.select(code, startTime, endTime);
	}
	
	public List<Collent> currentData(){
		return collentMapper.currentData();
	}
	
	public void insert(Collent collent) {
		collentMapper.insert(collent);
	}
	
	public void upload(Collent collent) {
		collentMapper.upload(collent);
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
}
