package com.aqara.common.service;

import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.*;
import com.aqara.common.entity.OpenCurtain;
import com.aqara.common.mapper.*;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.*;

@Service
public class OpenCurtainService {
	
	@Autowired
	OpenCurtainMapper OpenCurtainMapper;
	
	@Autowired
	HuobanProperties HuobanProperties;
	
	public List<OpenCurtain> currentData(){
		return OpenCurtainMapper.currentData();
	}
	
	public List<OpenCurtain> currentData1(){
		return OpenCurtainMapper.currentData1();
	}
	
	public void insert(OpenCurtain OpenCurtain) {
		OpenCurtainMapper.insert(OpenCurtain);
	}
	
	public void upload(OpenCurtain OpenCurtain) {
		OpenCurtainMapper.upload(OpenCurtain);
	}
	
	public void delete() {
		OpenCurtainMapper.delete();	
	}
	
	public void getCurtainList(String ticket) throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = CommonUtil.getCurtainData();
		String requestUrl = HuobanProperties.getSearchInfo() + "2100000015445679/find";
		JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
		JSONArray array = object.getJSONArray("items");
		for (int i = 0; i < array.size(); i++) {
			OpenCurtain OpenCurtain = new OpenCurtain();
			JSONObject obj = array.getJSONObject(i);
			JSONArray array1 = obj.getJSONArray("fields");
			OpenCurtain.setCreateTime(simpleDateFormat.parse(obj.getString("created_on")));
			OpenCurtain.setUpdateTime(simpleDateFormat.parse(obj.getString("updated_on")));
			for (int j = 0; j < array1.size(); j++) {
				JSONObject obj1 = array1.getJSONObject(j);
				String field_id = obj1.getString("field_id");
				JSONArray array2 = obj1.getJSONArray("values");
				JSONObject obj2 = array2.getJSONObject(0);
				if (field_id.equals("2200000148927095")) {
					OpenCurtain.setCode(obj2.getString("title"));
				} else if (field_id.equals("1102001117001102")) {
					OpenCurtain.setSales(obj2.getString("name"));
				} else if (field_id.equals("2200000159767482")) {
					OpenCurtain.setSupplier(obj2.getString("name"));
				} else if (field_id.equals("2200000159767483")) {
					OpenCurtain.setMerchantConfirm(obj2.getString("name"));
				} else if (field_id.equals("2200000148927632")) {
					OpenCurtain.setNumber(obj2.getString("value"));
				} else if (field_id.equals("1102001294000000")) {
					OpenCurtain.setDepartment(obj2.getString("title"));
				}
			}
			OpenCurtainMapper.upload(OpenCurtain);
		}
	}
	
	public String getCurtainData() {
		String str = "**开合帘5天未发货** \n";
		List<OpenCurtain> OpenCurtain = OpenCurtainMapper.currentData();
		if(OpenCurtain.size() == 0) {
			return "";
		}
		Map<String, Integer> map = new HashMap<>();
		OpenCurtain.forEach(name -> {
			Integer counts = map.get(name.getSales());
			if(counts == null) {
				counts = 0;
			}
			map.put(name.getSales(), counts + Integer.parseInt(name.getNumber()));
		});
		Map<String, Integer> map1 = CommonUtil.sortByValue(map);
		Iterator entries = map1.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String key = (String)entry.getKey();
		    Integer value = (Integer)entry.getValue();
		    str += ">" + key + ":<font color=\"comment\">" + value + "</font>\n";
		}
		return str;
	}
	
	public String getCurtainData1() {
		String str = "**开合帘3天未制作** \n";
		List<OpenCurtain> OpenCurtain = OpenCurtainMapper.currentData1();
		if(OpenCurtain.size() == 0) {
			return "";
		}
		Map<String, Integer> map = new HashMap<>();
		OpenCurtain.forEach(name -> {
			Integer counts = map.get(name.getSales());
			if(counts == null) {
				counts = 0;
			}
			map.put(name.getSales(), counts + Integer.parseInt(name.getNumber()));
		});
		Map<String, Integer> map1 = CommonUtil.sortByValue(map);
		Iterator entries = map1.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String key = (String)entry.getKey();
		    Integer value = (Integer)entry.getValue();
		    str += ">" + key + ":<font color=\"comment\">" + value + "</font>\n";
		}
		return str;
	}
}