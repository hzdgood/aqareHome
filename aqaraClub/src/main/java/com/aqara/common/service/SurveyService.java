package com.aqara.common.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aqara.common.entity.Survey;
import com.aqara.common.mapper.SurveyMapper;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.utils.CommonUtil;

@Service
public class SurveyService {
	
	@Autowired
	SurveyMapper surveyMapper;
	
	@Autowired
	HuobanProperties HuobanProperties;
	
	public List<Survey> select(String code, String startTime, String endTime){
		return surveyMapper.select(code, startTime, endTime);
	}
	
	public List<Survey> currentData(){
		return surveyMapper.currentData();
	}

	public void insert(Survey survey) {
		surveyMapper.insert(survey);
	}
	
	public void delete(Integer id) {
		surveyMapper.delete(id);
	}
	
	public void getSurveyList(String ticket) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = CommonUtil.getSurveyTime();
		String requestUrl = HuobanProperties.getSearchInfo() + "2100000015092715/find";
		JSONObject object = HttpService.getSchedule(requestUrl, ticket, JSONObject.parseObject(str));
		JSONArray array = object.getJSONArray("items");
		for (int i = 0; i < array.size(); i++) {
			Survey Survey = new Survey();
			JSONObject obj = array.getJSONObject(i);
			JSONArray array1 = obj.getJSONArray("fields");
			for (int j = 0; j < array1.size(); j++) {
				JSONObject obj1 = array1.getJSONObject(j);
				String field_id = obj1.getString("field_id");
				JSONArray array2 = obj1.getJSONArray("values");
				JSONObject obj2 = array2.getJSONObject(0);
				if (field_id.equals("2200000146063366")) {
					Survey.setCode(obj2.getString("title"));
				} else if (field_id.equals("1101001101001107")) {
					Survey.setSales(obj2.getString("title"));
				} else if (field_id.equals("1101001294001101")) {
					Survey.setDepartment(obj2.getString("title"));
				} else if (field_id.equals("2200000168338254")) {
					Survey.setAppointmentTime(simpleDateFormat.parse(obj2.getString("value")));
				} else if (field_id.equals("2200000168338022")) {
					Survey.setEstimatedDuration(obj2.getString("value"));
				} else if (field_id.equals("2200000168613835")) {
					Survey.setSurveyTask(obj2.getString("name"));
				}
				Survey.setCreateName("上海汇社");
			}
			surveyMapper.insert(Survey);
		}
	}
	
	public String getSurveyData() {
		String str = "**今日CRM新增工勘TOP** \n";
		List<Survey> Survey = surveyMapper.currentData();
		if(Survey.size() == 0) {
			return "";
		}
		Map<String, Integer> map = new HashMap<>();
		Survey.forEach(name -> {
			Integer counts = map.get(name.getSales());
			map.put(name.getSales(), counts == null ? 1 : ++counts);
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
