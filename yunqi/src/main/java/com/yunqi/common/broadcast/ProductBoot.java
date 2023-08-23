package com.yunqi.common.broadcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.yunqi.common.service.*;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class ProductBoot {

    @Autowired
    private ProductService ProductService;

    @Scheduled(cron = "0 00 21 * * ?")
    private void uploadExcel() {

    }
}
