package com.aqara.common.broadcast;

import com.aqara.common.entity.Huoban;
import com.aqara.common.service.*;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class WorkSheet {

    @Autowired
    HuobanService HuobanService;


    @Autowired
    WorkSheetService WorkSheetService;

    @Autowired
    HttpService HttpService;

    @Scheduled(cron = "0 50 20 * * ?")
    private void currentWorkData() {
        List<Huoban> list = HuobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        try {
            String str = CommonUtil.getCurrentWorkData();
            String str1 = CommonUtil.getTomorrowWorkData();
            WorkSheetService.getCurrentWork(ticket, str);
            WorkSheetService.getCurrentWork(ticket, str1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 今日工单
     * */
    @Scheduled(cron = "0 00 21 * * ?")
    private void currentWorkSend() {
        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f";
        // String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1fc95ced-6cb2-406a-b204-a109202dfded"; // 测试
        String workSheet = WorkSheetService.getWorkSend();
        HttpService.workRequset(workSheet, WX_TOKEN);
    }

    /**
     * 明日工单
     * */
    @Scheduled(cron = "0 01 21 * * ?")
    private void currentWorkSend1() {
        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f";
        // String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1fc95ced-6cb2-406a-b204-a109202dfded"; // 测试
        String workSheet = WorkSheetService.getWorkSendTow();
        HttpService.workRequset(workSheet, WX_TOKEN);
    }
}
