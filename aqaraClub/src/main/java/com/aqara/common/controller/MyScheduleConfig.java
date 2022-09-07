package com.aqara.common.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import com.aqara.common.entity.*;
import com.aqara.common.service.*;

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
		String customer = customerService.getCustomerData();
		String collent = CollentService.getCollentData();
		String survey = SurveyService.getSurveyData();
		String url = "[查看详情](https://app.huoban.com/home)";
		HttpService.workRequset(customer + "\n" + collent + "\n" + survey + "\n" + url);
	}
	
	@Scheduled(cron = "0 30 08 * * ?")
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
	
	@Scheduled(cron = "0 00 08 * * ?")
	private void myTasks3() {
		OpenCurtainService.delete();
		RollerShutterService.delete();
	}
}