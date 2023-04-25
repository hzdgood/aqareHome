package com.aqara.common.broadcast;

import com.aqara.common.excel.SchemeExcel;
import com.aqara.common.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class Scheme {
    @Autowired
    SchemeService SchemeService;

    @Scheduled(cron = "0 43 17 * * ?")
    private void SchemeData() {
        File file = new File("D:\\客户方案_20230414150758.xlsx");
        if (file.exists()) {
            System.out.print("OK");
            try {
                SchemeExcel.schemeToExcel(file, SchemeService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
