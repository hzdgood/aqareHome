package com.aqara.common.broadcast;

import com.aqara.common.entity.Huoban;
import com.aqara.common.excel.CustomerExcel;
import com.aqara.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class CustomerBoot {

    @Autowired
    HttpService HttpService;

    @Autowired
    HuobanService HuobanService;

    @Autowired
    SurveyService SurveyService;

    @Autowired
    CollentService CollentService;

    @Autowired
    WeixinService WeixinService;

    @Autowired
    CustomerService CustomerService;

    @Autowired
    CensusService CensusService;

    String url = "[查看详情](https://app.huoban.com/home)";

    @Scheduled(cron = "0 30 21 * * ?")
    private void currentDayCustom() {
        List<Huoban> list = HuobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        try {
            SurveyService.getSurveyList(ticket);
            CustomerService.getCustomList(ticket);
            CollentService.getCollentList(ticket);
            WeixinService.getWeixinData(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 今日的信息机器人
     */
    @Scheduled(cron = "0 35 21 * * ?")
    private void currentDayBroadcast() {
        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=283f104b-f171-41a0-a7cc-fd977884330c";
        String CensusData = CensusService.getCensusData();
        String customer = CustomerService.getCurrentData();
        String collent = CollentService.getCollentData();
        String survey = SurveyService.getSurveyData();
        String resStr = "";
        if (!CensusData.equals("")) {
            resStr += CensusData + "\n";
        }
        if (!customer.equals("")) {
            resStr += customer + "\n";
        }
        if (!collent.equals("")) {
            resStr += collent + "\n";
        }
        if (!survey.equals("")) {
            resStr += survey + "\n";
        }
        resStr += url;
        HttpService.workRequset(resStr, WX_TOKEN);
    }
}
