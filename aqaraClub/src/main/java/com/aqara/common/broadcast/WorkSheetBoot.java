package com.aqara.common.broadcast;

import com.aqara.common.entity.Huoban;
import com.aqara.common.service.HttpService;
import com.aqara.common.service.HuobanService;
import com.aqara.common.service.WorkSheetService;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class WorkSheetBoot {

    @Autowired
    HuobanService HuobanService;

    @Autowired
    WorkSheetService WorkSheetService;

    @Autowired
    HttpService HttpService;

    @Scheduled(cron = "0 55 20 * * ?")
    private void currentWorkData() {
        List<Huoban> list = HuobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        try {
            String str = CommonUtil.getWorkData();
            String str2 = CommonUtil.getNoCompleteWorkData();
            WorkSheetService.getCurrentWork(ticket, str);
            WorkSheetService.getCurrentWork(ticket, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 55 17 * * ?")
    private void currentWorkData1() {
        List<Huoban> list = HuobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        try {
            String str = CommonUtil.getNoCompleteWorkData();
            WorkSheetService.getCurrentWork(ticket, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 今日明日工单
     */
    @Scheduled(cron = "0 00 21 * * ?")
    private void currentWorkSend() {
        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f";
        // String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1fc95ced-6cb2-406a-b204-a109202dfded"; // 测试
        String WX_DEPART = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=0adf9002-d736-4921-b35f-f36c6429b097";
        String workSheet = WorkSheetService.getWorkSend();
        String workSheet1 = WorkSheetService.getWorkSendTow();
        String workSheet2 = WorkSheetService.getNoComplete();
        HttpService.workRequset(workSheet, WX_TOKEN);
        HttpService.workRequset(workSheet, WX_DEPART);
        HttpService.workRequset(workSheet1, WX_TOKEN);
        HttpService.workRequset(workSheet1, WX_DEPART);
        HttpService.workRequset(workSheet2, WX_TOKEN);
        WorkSheetService.deleteData();
    }

    @Scheduled(cron = "0 00 18 * * ?")
    private void currentWorkSend3() {
        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f";
        // String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1fc95ced-6cb2-406a-b204-a109202dfded"; // 测试
        String workSheet = WorkSheetService.getNoComplete();
        HttpService.workRequset(workSheet, WX_TOKEN);
        WorkSheetService.deleteData();
    }
}
