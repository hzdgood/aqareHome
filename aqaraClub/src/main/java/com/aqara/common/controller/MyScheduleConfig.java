package com.aqara.common.controller;

import com.aqara.common.entity.Huoban;
import com.aqara.common.entity.Project;
import com.aqara.common.service.*;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

//@Configuration // 标记配置类
//@EnableScheduling // 开启定时任务
public class MyScheduleConfig {

//    @Autowired
//    HuobanService huobanService;
//
//    @Autowired
//    SurveyService SurveyService;
//
//    @Autowired
//    CollentService CollentService;
//
//    @Autowired
//    CustomerService customerService;
//
//    @Autowired
//    HttpService HttpService;
//
//    @Autowired
//    ProjectService ProjectService;
//
//    @Autowired
//    WeixinService WeixinService;
//
//    @Autowired
//    CensusService CensusService;
//
//    @Autowired
//    WorkSheetService WorkSheetService;
//
//    @Autowired
//    UserService UserService;

//    String url = "[查看详情](https://app.huoban.com/home)";
//
//    @Scheduled(cron = "0 50 20 * * ?")
//    private void currentWorkData() {
//        List<Huoban> list = huobanService.select();
//        Huoban Huoban = list.get(list.size() - 1);
//        String ticket = Huoban.getTicket();
//        try {
//            String str = CommonUtil.getCurrentWorkData();
//            String str1 = CommonUtil.getTomorrowWorkData();
//            WorkSheetService.getCurrentWork(ticket, str);
//            WorkSheetService.getCurrentWork(ticket, str1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 今日工单
//     * */
//    @Scheduled(cron = "0 00 21 * * ?")
//    private void currentWorkSend() {
//        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f";
//        String workSheet = WorkSheetService.getWorkSend();
//        HttpService.workRequset(workSheet, WX_TOKEN);
//    }
//
//    /**
//     * 明日工单
//     * */
//    @Scheduled(cron = "0 01 21 * * ?")
//    private void currentWorkSend1() {
//        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f";
//        String workSheet = WorkSheetService.getWorkSend1();
//        HttpService.workRequset(workSheet, WX_TOKEN);
//    }

//    @Scheduled(cron = "0 30 21 * * ?")
//    private void currentDayCustom() {
//        List<Huoban> list = huobanService.select();
//        Huoban Huoban = list.get(list.size() - 1);
//        String ticket = Huoban.getTicket();
//        try {
//            SurveyService.getSurveyList(ticket);
//            customerService.getCustomList(ticket);
//            CollentService.getCollentList(ticket);
//            WeixinService.getWeixinData(ticket);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 今日的信息机器人
//     * */
//    @Scheduled(cron = "0 35 21 * * ?")
//    private void currentDayBroadcast() {
//        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=283f104b-f171-41a0-a7cc-fd977884330c";
//        String CensusData = CensusService.getCensusData();
//        String customer = customerService.getCurrentData();
//        String collent = CollentService.getCollentData();
//        String survey = SurveyService.getSurveyData();
//        String resStr = "";
//        if (!CensusData.equals("")) {
//            resStr += CensusData + "\n";
//        }
//        if (!customer.equals("")) {
//            resStr += customer + "\n";
//        }
//        if (!collent.equals("")) {
//            resStr += collent + "\n";
//        }
//        if (!survey.equals("")) {
//            resStr += survey + "\n";
//        }
//        resStr += url;
//        HttpService.workRequset(resStr, WX_TOKEN);
//    }

//    @Scheduled(cron = "0 45 09 * * ?")
//    private void projectSelect() {
//        List<Huoban> list = huobanService.select();
//        Huoban Huoban = list.get(list.size() - 1);
//        String ticket = Huoban.getTicket();
//        try {
//            ProjectService.getProjectList(ticket);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 项目提醒机器人
//     * */
//    @Scheduled(cron = "0 00 10 * * ?")
//    private void projectBroadcast() {
//        List<Project> Project = ProjectService.currentData();
//        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=0adf9002-d736-4921-b35f-f36c6429b097";
//        String str0 = "**一个月内未跟踪项目** \n";
//        String str = ProjectService.getCurtainData("徐汇绿地缤纷城店", Project) + "\n";
//        String str1 = ProjectService.getCurtainData("普陀绿地缤纷城店", Project) + "\n";
//        String str2 = ProjectService.getCurtainData("虹口太阳宫店", Project) + "\n";
//        String str3 = ProjectService.getCurtainData("闵行龙之梦店", Project) + "\n";
//        String res = str0 + str + str1 + str2 + str3;
//        HttpService.workRequset(res, WX_TOKEN);
//    }
}