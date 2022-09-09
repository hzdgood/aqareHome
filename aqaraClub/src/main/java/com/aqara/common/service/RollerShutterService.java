package com.aqara.common.service;

import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.RollerShutter;
import com.aqara.common.mapper.*;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;

@Service
public class RollerShutterService {
	
	@Autowired
	RollerShutterMapper RollerShutterMapper;
	
	@Autowired
	HuobanProperties HuobanProperties;
	
	public List<RollerShutter> currentData(){
		return RollerShutterMapper.currentData();
	}
	
	public void insert(RollerShutter RollerShutter) {
		RollerShutterMapper.insert(RollerShutter);
	}
	
	public void upload(RollerShutter RollerShutter) {
		RollerShutterMapper.upload(RollerShutter);
	}

	public void delete() {
		RollerShutterMapper.delete();
	}
	
	public void getShutterList(String ticket) throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = CommonUtil.getShutterData();
		String requestUrl = HuobanProperties.getSearchInfo() + "2100000015288439/find";
		JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
		JSONArray array = object.getJSONArray("items");
		for (int i = 0; i < array.size(); i++) {
			RollerShutter RollerShutter = new RollerShutter();
			JSONObject obj = array.getJSONObject(i);
			JSONArray array1 = obj.getJSONArray("fields");
			RollerShutter.setCreateTime(simpleDateFormat.parse(obj.getString("created_on")));
			RollerShutter.setUpdateTime(simpleDateFormat.parse(obj.getString("updated_on")));
			for (int j = 0; j < array1.size(); j++) {
				JSONObject obj1 = array1.getJSONObject(j);
				String field_id = obj1.getString("field_id");
				JSONArray array2 = obj1.getJSONArray("values");
				JSONObject obj2 = array2.getJSONObject(0);
				if (field_id.equals("2200000147636558")) {
					RollerShutter.setCode(obj2.getString("title"));
				} else if (field_id.equals("1101031294001102")) {
					RollerShutter.setSales(obj2.getString("name"));
				} else if (field_id.equals("1101031294000000")) {
					RollerShutter.setDepartment(obj2.getString("title"));
				} else if (field_id.equals("1138001108000000")) {
					RollerShutter.setSupplier(obj2.getString("title"));
				} else if (field_id.equals("2200000159475098")) {
					RollerShutter.setMerchantConfirm(obj2.getString("name"));
				} else if (field_id.equals("2200000147636568")) {
					RollerShutter.setNumber(obj2.getString("value"));
				}
			}
			RollerShutterMapper.upload(RollerShutter);
		}
	}
}
