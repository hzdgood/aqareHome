package com.aqara.common.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import com.aqara.common.entity.*;
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
	OpenCurtainService OpenCurtainService;
	
	@Autowired
	RollerShutterService RollerShutterService;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Scheduled(cron = "0 30 20 * * ?")
	private void myTasks() {
		System.out.println("执行定时任务 " + LocalDateTime.now());
		List<Huoban> list = huobanService.select();
		Huoban Huoban = list.get(list.size() - 1);
		String ticket = Huoban.getTicket();
		try {
			SurveyService.getSurveyList(ticket);
			customerService.getCustomList(ticket);
			CollentService.getCollentList(ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 35 20 * * ?")
	private void myTasks1() {
		String customer = getCustomerData();
		String collent = getCollentData();
		String survey = getSurveyData();
		String url = "[查看详情](https://app.huoban.com/home)";
		HttpService.workRequset(customer + "\n" + collent + "\n" + survey + "\n" + url);
	}
	
	@Scheduled(cron = "0 33 16 * * ?")
	private void myTasks2() {
		try {
			List<Huoban> list = huobanService.select();
			Huoban Huoban = list.get(list.size() - 1);
			String ticket = Huoban.getTicket();
			OpenCurtainService.getCurtainList(ticket);
			RollerShutterService.getShutterList(ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron = "0 50 14 * * ?")
	private void myTasks3() {
		
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
}