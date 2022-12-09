package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.User;
import com.aqara.common.mapper.UserMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	HuobanProperties HuobanProperties;

	public List<User> select(String username, String engName) {
		return userMapper.select(username, engName);
	}
	
	public List<User> selectCode(String code) {
		return userMapper.selectCode(code);
	}
	
	public void insert(User user) {
		userMapper.insert(user);
	}

	public void update(User user) {
		userMapper.update(user);
	}
	
	public void delete() {
		userMapper.delete();
	}

	public void synchronize(String ticket) {
		String str = CommonUtil.getUserJson();
		String requestUrl = HuobanProperties.getSearchInfo() + "2100000014955862/find";
		JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
		JSONArray array = object.getJSONArray("items");
		for (int i = 0; i < array.size(); i++) {
			User User = new User();
			JSONObject obj = array.getJSONObject(i);
			JSONArray array1 = obj.getJSONArray("fields");
			for (int j = 0; j < array1.size(); j++) {
				JSONObject obj1 = array1.getJSONObject(j);
				String field_id = obj1.getString("field_id");
				JSONArray array2 = obj1.getJSONArray("values");
				JSONObject obj2 = array2.getJSONObject(0);
				if (field_id.equals("2200000144968453")) {
					User.setUserName(obj2.getString("name"));
					User.setCode(obj2.getString("user_id"));
				} else if (field_id.equals("2200000166537030")) {
					User.setEngName(obj2.getString("value"));
				}  else if (field_id.equals("2200000144968454")) {
					User.setSex(obj2.getString("name"));
				} else if (field_id.equals("2200000257456339")) {
					User.setStation(obj2.getString("name"));
				} else if (field_id.equals("2200000160826904")) {
					User.setDepartment(obj2.getString("title"));
				} else if (field_id.equals("2200000297757385")) {
					User.setCompany(obj2.getString("title"));
				}
				User.setStatus("在职");
			}
			userMapper.insert(User);
		}
	}
}

