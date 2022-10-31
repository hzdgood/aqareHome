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
	CensusService CensusService;
	
	@Autowired
	OpenCurtainService OpenCurtainService;
	
	@Autowired
	RollerShutterService RollerShutterService;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Scheduled(cron = "0 30 21 * * ?")
	private void currentDayCustom() {
		List<Huoban> list = huobanService.select();
		Huoban Huoban = list.get(list.size() - 1);
		String ticket = Huoban.getTicket();
		try {
			SurveyService.getSurveyList(ticket);
			customerService.getCustomList(ticket);
			CollentService.getCollentList(ticket);
			WeixinService.getWeixinData(ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 35 21 * * ?")
	private void currentDayBroadcast() {
		String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=283f104b-f171-41a0-a7cc-fd977884330c";
		String CensusData = CensusService.getCensusData();
		String customer = customerService.getCurrentData();
		String collent = CollentService.getCollentData();
		String survey = SurveyService.getSurveyData();
		String url = "[查看详情](https://app.huoban.com/home)";
		String resStr = "";
		if(!CensusData.equals("")) {
			resStr += CensusData + "\n";
		} 
		if(!customer.equals("")) {
			resStr += customer + "\n";
		} 
		if(!collent.equals("")) {
			resStr += collent + "\n";
		} 
		if(!survey.equals("")) {
			resStr += survey + "\n";
		} 
		resStr += url;
		HttpService.workRequset(resStr, WX_TOKEN);
	}
	
	@Scheduled(cron = "0 00 14 ? * MON")
	private void weekReboot() {
		String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=283f104b-f171-41a0-a7cc-fd977884330c";
		String CensusData = CensusService.getWeekData();
		String customer = customerService.getWeekData();
		String collent = CollentService.getWeekData();
		// String survey = SurveyService.getWeekData();
		String url = "[查看详情](https://app.huoban.com/home)";
		String resStr = "";
		if(!CensusData.equals("")) {
			resStr += CensusData + "\n";
		}
		if(!customer.equals("")) {
			resStr += customer + "\n";
		} 
		if(!collent.equals("")) {
			resStr += collent + "\n";
		} 
//		if(!survey.equals("")) {
//			resStr += survey + "\n";
//		}
		resStr += url;
		System.out.println(resStr);
		HttpService.workRequset(resStr, WX_TOKEN);
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
	
	// @Scheduled(cron = "0 34 14 * * ?")
//	private void myTasks2() {
//		List<Huoban> list = huobanService.select();
//		Huoban Huoban = list.get(list.size() - 1);
//		String ticket = Huoban.getTicket();
//		try {
//			OpenCurtainService.getCurtainList(ticket);
//			RollerShutterService.getShutterList(ticket);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	// @Scheduled(cron = "0 32 14 * * ?")
//	private void deleteCurtain() {
//		OpenCurtainService.delete();
//		RollerShutterService.delete();
//	}
//	
//	// @Scheduled(cron = "0 28 16 * * ?")
//	private void weixinInfo() {
//		String WX_TOKEN = "";
//		String str1 = OpenCurtainService.getCurtainData1();
//		String str = OpenCurtainService.getCurtainData();
//		String str2 = RollerShutterService.getShutterData();
//		String str3 = RollerShutterService.getShutterData1();
//		HttpService.workRequset(str, WX_TOKEN);
//		HttpService.workRequset(str1, WX_TOKEN);
//		HttpService.workRequset(str2, WX_TOKEN);
//		HttpService.workRequset(str3, WX_TOKEN);
//	}
}