package com.yunqi.common.broadcast;

import com.yunqi.common.service.HttpService;
import com.yunqi.common.service.HuobanService;
import com.yunqi.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class WorkSheetBoot {

//    @Autowired
//    HuobanService HuobanService;
//
//    @Autowired
//    WorkSheetService WorkSheetService;
//
//    @Autowired
//    HttpService HttpService;
//
//    /**
//     * 今日明日工单
//     */
//    @Scheduled(cron = "0 00 21 * * ?")
//    private void currentWorkSend() {
//        String workSheet = WorkSheetService.getWorkSend();
//        String workSheet1 = WorkSheetService.getWorkSendTow();
//        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f"; //技术群
//        String WX_DEPART = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=0adf9002-d736-4921-b35f-f36c6429b097"; //店长群
//        String WX_XH = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=a64c0c5f-914a-4acf-9f27-e3ada0e5e043"; //徐汇
//        String WX_PY = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=ebab5fdf-2191-482c-9ba5-39b0263a165c"; //普陀
//        String WX_TY = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=78919d9d-37c5-47df-8f4a-05d6fe6fb08c"; //太阳宫
//        String WX_MH = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=2366b22e-9811-4539-b501-4849d378924a"; //闵行
//        HttpService.workRequset(workSheet, WX_TOKEN);
//        HttpService.workRequset(workSheet, WX_DEPART);
//        HttpService.workRequset(workSheet1, WX_TOKEN);
//        HttpService.workRequset(workSheet1, WX_DEPART);
//        HttpService.workRequset(workSheet, WX_XH);
//        HttpService.workRequset(workSheet1, WX_XH);
//        HttpService.workRequset(workSheet, WX_PY);
//        HttpService.workRequset(workSheet1, WX_PY);
//        HttpService.workRequset(workSheet, WX_TY);
//        HttpService.workRequset(workSheet1, WX_TY);
//        HttpService.workRequset(workSheet, WX_MH);
//        HttpService.workRequset(workSheet1, WX_MH);
//    }
//
//    /**
//     * 没有预约的工单
//     */
//    @Scheduled(cron = "0 30 21 * * ?")
//    private void currentWorkSend3() {
//        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=04132b83-0692-47e0-b54d-6326ea8a921f"; //技术群
//        // String WX_TOKEN1 = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1fc95ced-6cb2-406a-b204-a109202dfded"; // 测试
//        String WX_DEPART = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=0adf9002-d736-4921-b35f-f36c6429b097"; //店长群
//        String workSheet = WorkSheetService.getNoVisit();
//        String workSheet1 = WorkSheetService.getNotComplete();
//        HttpService.workRequset(workSheet, WX_TOKEN);
//        HttpService.workRequset(workSheet1, WX_DEPART);
//    }
//
//    //    @Scheduled(cron = "0 00 22 * * ?")
//    private void currentWorkSyn() {
//        WorkSheetService.workSheetSyn();
//    }
}
