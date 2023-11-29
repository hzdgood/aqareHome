package com.aqara.common.broadcast;

import com.aqara.common.entity.Huoban;
import com.aqara.common.entity.Project;
import com.aqara.common.service.HttpService;
import com.aqara.common.service.HuobanService;
import com.aqara.common.service.ProjectService;
import com.aqara.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class ProjectBoot {

    @Autowired
    HuobanService HuobanService;

    @Autowired
    ProjectService ProjectService;

    @Autowired
    HttpService HttpService;

    @Scheduled(cron = "0 50 09 * * ?")
    private void projectSelect() {
        List<Huoban> list = HuobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        try {
            String str = CommonUtil.getProjectData();
            ProjectService.getProjectList(ticket, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 项目提醒机器人
     */
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
        ProjectService.deleteData();
    }

    @Scheduled(cron = "0 20 09 * * ?")
    private void subPayProject() {
        List<Huoban> list = HuobanService.select();
        Huoban Huoban = list.get(list.size() - 1);
        String ticket = Huoban.getTicket();
        try {
            String str = CommonUtil.getSubPayProject();
            ProjectService.getProjectList(ticket, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 30 09 * * ?")
    private void subProjectBroadcast() {
        List<Project> Project = ProjectService.currentData();
        // String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=0adf9002-d736-4921-b35f-f36c6429b097";
        String WX_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=a64c0c5f-914a-4acf-9f27-e3ada0e5e043"; //徐汇
        String WX_TOKEN1 = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=ebab5fdf-2191-482c-9ba5-39b0263a165c"; //普陀
        String WX_TOKEN2 = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=78919d9d-37c5-47df-8f4a-05d6fe6fb08c"; //太阳宫
        String WX_TOKEN3 = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=2366b22e-9811-4539-b501-4849d378924a"; //闵行

        String str0 = "**调试完成未结清款项项目** \n";
        String str = ProjectService.getSubProject("徐汇绿地缤纷城店", Project) + "\n";
        String str1 = ProjectService.getSubProject("普陀绿地缤纷城店", Project) + "\n";
        String str2 = ProjectService.getSubProject("虹口太阳宫店", Project) + "\n";
        String str3 = ProjectService.getSubProject("闵行龙之梦店", Project) + "\n";

        HttpService.workRequset(str0 + str, WX_TOKEN);
        HttpService.workRequset(str0 + str1, WX_TOKEN1);
        HttpService.workRequset(str0 + str2, WX_TOKEN2);
        HttpService.workRequset(str0 + str3, WX_TOKEN3);
        ProjectService.deleteData();
    }
}
