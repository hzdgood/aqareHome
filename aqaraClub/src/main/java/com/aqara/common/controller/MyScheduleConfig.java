package com.aqara.common.controller;

import com.alibaba.fastjson.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import com.aqara.common.entity.*;
import com.aqara.common.properties.HuobanProperties;
import com.aqara.common.service.*;
import com.aqara.common.utils.*;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class MyScheduleConfig {

	@Autowired
	HuobanService huobanService;

	@Autowired
	SurveyService SurveyService;

	@Autowired
	CollentService CollentService;

	@Autowired
	CustomerService customerService;

	@Autowired
	HttpService HttpService;

	@Autowired
	HuobanProperties HuobanProperties;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Scheduled(cron = "0 33 17 * * ?")
	private void myTasks() {
		System.out.println("执行定时任务 " + LocalDateTime.now());
		List<Huoban> list = huobanService.select();
		Huoban Huoban = list.get(list.size() - 1);
		String ticket = Huoban.getTicket();
		try {
			getSurveyList(ticket);
			getCustomList(ticket);
			getCollentList(ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 35 17 * * ?")
	private void myTasks1() {
		String customer = getCustomerData();
		String collent = getCollentData();
		String survey = getSurveyData();
		String url = "[查看详情](https://app.huoban.com/home)";
		HttpService.workRequset(customer + "\n" + collent + "\n" + survey + "\n" + url);
	}
	
	private String getSurveyData() {
		String str = "**今日CRM新增工勘TOP** \n";
		List<Survey> Survey = SurveyService.currentData();
		Map<String, Integer> map = new HashMap<>();
		Survey.forEach(name -> {
			Integer counts = map.get(name.getSales());
			map.put(name.getSales(), counts == null ? 1 : ++counts);
		});
		Map<String, Integer> map1 = MapSortUtil.sortByValue(map);
		Iterator entries = map1.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String key = (String)entry.getKey();
		    Integer value = (Integer)entry.getValue();
		    str += ">" + key + ":<font color=\"comment\">" + value + "</font>\n";
		}
		return str;
	}
	
	private String getCustomerData() {
		String str = "**今日CRM新增客户TOP** \n";
		List<Customer> customer = customerService.currentData();
		Map<String, Integer> map = new HashMap<>();
		customer.forEach(name -> {
			Integer counts = map.get(name.getSales());
			map.put(name.getSales(), counts == null ? 1 : ++counts);
		});
		Map<String, Integer> map1 = MapSortUtil.sortByValue(map);
		Iterator entries = map1.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String key = (String)entry.getKey();
		    Integer value = (Integer)entry.getValue();
		    str += ">" + key + ":<font color=\"comment\">" + value + "</font>\n";
		}
		return str;
	}

	private String getCollentData() {
		String str = "**今日CRM新增全款TOP** \n";
		List<Collent> collent = CollentService.currentData();
		Map<String, Integer> map = new HashMap<>();
		collent.forEach(name -> {
			Integer counts = map.get(name.getSales());
			map.put(name.getSales(), counts == null ? 1 : ++counts);
		});
		Map<String, Integer> map1 = MapSortUtil.sortByValue(map);
		Iterator entries = map1.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String key = (String)entry.getKey();
		    Integer value = (Integer)entry.getValue();
		    str += ">" + key + ":<font color=\"comment\">" + value + "</font>\n";
		}
		return str;
	}

	private void getSurveyList(String ticket) throws Exception {
		String str = "{\"where\":{\"and\":[{\"field\":2200000168338254,\"query\":{\"eq\":\"today\"}}]},\"offset\":0,\"limit\":20,\"order_by\":[{\"field\":2200000169723711,\"sort\":\"asc\"}]}";
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
			SurveyService.insert(Survey);
		}
	}

	private void getCustomList(String ticket) {
		String requestUrl = HuobanProperties.getSearchInfo() + "2100000014955786/find";
		JSONObject object = HttpService.getSchedule(requestUrl, ticket, getDataJson());
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
			customerService.insert(Customer);
		}
	}

	private void getCollentList(String ticket) {
		String requestUrl = HuobanProperties.getSearchInfo() + "2100000015000019/find";
		JSONObject object = HttpService.getSchedule(requestUrl, ticket, getDataJson());
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
			CollentService.insert(Collent);
		}
	}
	
	public static JSONObject getDataJson() {
		JSONObject o1 = new JSONObject();
		o1.put("eq", "today");
		JSONObject o2 = new JSONObject();
		o2.put("field", "created_on");
		o2.put("query", o1);
		JSONArray array = new JSONArray();
		array.add(o2);
		JSONObject o3 = new JSONObject();
		o3.put("and", array);
		JSONObject obj = new JSONObject();
		obj.put("where", o3);
		obj.put("offset", 0);
		obj.put("limit", 50);
		return obj;
	}
}