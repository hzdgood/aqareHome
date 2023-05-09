package com.aqara.common.broadcast;

import com.aqara.common.excel.CollentExcel;
import com.aqara.common.service.CollentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class Collent {
    @Autowired
    CollentService CollentService;

    private void collentData() {
        File file = new File("D:\\收款表_20230506171943.xlsx");
        if (file.exists()) {
            System.out.print("OK");
            try {
                CollentExcel.CollentExcel(file, CollentService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
