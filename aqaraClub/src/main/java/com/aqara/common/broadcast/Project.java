package com.aqara.common.broadcast;

import com.aqara.common.entity.Huoban;
import com.aqara.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class Project {

    @Autowired
    HuobanService HuobanService;

    @Autowired
    ProjectService ProjectService;

    @Autowired
    HttpService HttpService;

    @Scheduled(cron = "0 45 09 * * ?")
    private void projectSelect() {
        List<Huoban> list = HuobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        try {
            ProjectService.getProjectList(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 项目提醒机器人
     */
    @Scheduled(cron = "0 00 10 * * ?")
    private void projectBroadcast() {
        List<com.aqara.common.entity.Project> Project = ProjectService.currentData();
        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=0adf9002-d736-4921-b35f-f36c6429b097";
        String str0 = "**一个月内未跟踪项目** \n";
        String str = ProjectService.getCurtainData("徐汇绿地缤纷城店", Project) + "\n";
        String str1 = ProjectService.getCurtainData("普陀绿地缤纷城店", Project) + "\n";
        String str2 = ProjectService.getCurtainData("虹口太阳宫店", Project) + "\n";
        String str3 = ProjectService.getCurtainData("闵行龙之梦店", Project) + "\n";
        String res = str0 + str + str1 + str2 + str3;
        HttpService.workRequset(res, WX_TOKEN);
    }
}
