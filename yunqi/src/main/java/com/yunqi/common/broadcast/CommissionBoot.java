package com.yunqi.common.broadcast;

import com.yunqi.common.excel.CommissionExcel;
import com.yunqi.common.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class CommissionBoot {

    @Autowired
    private CommissionService CommissionService;

    @Scheduled(cron = "0 45 17 * * ?")
    public void uploadExcel() {
        File file = new File("D:\\download\\物料技术提成表.xlsx");
        CommissionExcel.commissionExcel(file, CommissionService);
    }
}
