package com.aqara.common.controller;

import java.text.SimpleDateFormat;
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
	ProjectService ProjectService;
	
	@Autowired
	WeixinService WeixinService;
	
	@Autowired
	OpenCurtainService OpenCurtainService;
	
	@Autowired
	RollerShutterService RollerShutterService;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Scheduled(cron = "0 30 20 * * ?")
	private void currentDayCustom() {
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
	private void currentDayBroadcast() {
		String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=283f104b-f171-41a0-a7cc-fd977884330c";
		String customer = customerService.getCustomerData();
		String collent = CollentService.getCollentData();
		String survey = SurveyService.getSurveyData();
		String url = "[查看详情](https://app.huoban.com/home)";
		HttpService.workRequset(customer + "\n" + collent + "\n" + survey + "\n" + url, WX_TOKEN);
	}
	
	@Scheduled(cron = "0 00 09 * * ?")
	private void projectDelete() {
		ProjectService.delete();
	}
	
	@Scheduled(cron = "0 30 09 * * ?")
	private void projectSelect() {
		List<Huoban> list = huobanService.select();
		Huoban Huoban = list.get(list.size() - 1);
		String ticket = Huoban.getTicket();
		try {
			ProjectService.getProjectList(ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron = "0 00 10 * * ?")
	private void projectBroadcast() {
		List<Project> Project = ProjectService.currentData();
		String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=0adf9002-d736-4921-b35f-f36c6429b097";
		String str0 = "**一个月内未跟踪项目** \n";
		String str = ProjectService.getCurtainData("徐汇绿地缤纷城店", Project) + "\n";
		String str1 = ProjectService.getCurtainData("普陀绿地缤纷城店", Project) + "\n";
		String str2 = ProjectService.getCurtainData("虹口太阳宫店", Project) + "\n";
		String str3 = ProjectService.getCurtainData("闵行龙之梦店", Project) + "\n";
		String res = str0 + str + str1 + str2 + str3;
		HttpService.workRequset(res, WX_TOKEN);
	}
	
	//@Scheduled(cron = "0 34 14 * * ?")
	private void myTasks2() {
		List<Huoban> list = huobanService.select();
		Huoban Huoban = list.get(list.size() - 1);
		String ticket = Huoban.getTicket();
		try {
			OpenCurtainService.getCurtainList(ticket);
			RollerShutterService.getShutterList(ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Scheduled(cron = "0 32 14 * * ?")
	private void deleteCurtain() {
		OpenCurtainService.delete();
		RollerShutterService.delete();
	}
	
	@Scheduled(cron = "0 44 15 * * ?")
	private void weixinInfo() {
		List<Huoban> list = huobanService.select();
		Huoban Huoban = list.get(list.size() - 1);
		String ticket = Huoban.getTicket();
		
		String str = WeixinService.getWeixinStr(ticket);
		System.out.println(str);
		
//		String str1 = OpenCurtainService.getCurtainData1();
//		System.out.println(str1);
//		String str = OpenCurtainService.getCurtainData();
//		String str2 = RollerShutterService.getShutterData();
//		String str3 = RollerShutterService.getShutterData1();
//		HttpService.workRequset1(str);
//		HttpService.workRequset1(str1);
//		HttpService.workRequset2(str2);
//		HttpService.workRequset2(str3);
	}
}