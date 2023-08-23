package com.yunqi.common.broadcast;

import com.yunqi.common.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class SchemeBoot {

    @Autowired
    private SchemeService SchemeService;

    @Scheduled(cron = "0 00 21 * * ?")
    private void uploadExcel() {

    }
}
