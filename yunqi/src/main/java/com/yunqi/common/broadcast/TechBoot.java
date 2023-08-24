package com.yunqi.common.broadcast;

import com.yunqi.common.excel.TechExcel;
import com.yunqi.common.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileNotFoundException;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class TechBoot {
    @Autowired
    private TechService TechService;

    @Scheduled(cron = "0 00 21 * * ?")
    private void uploadExcel() {
        File file = new File("");
        try {
            TechExcel.TechExcel(file, TechService);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
