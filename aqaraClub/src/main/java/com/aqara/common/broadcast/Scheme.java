package com.aqara.common.broadcast;

import com.aqara.common.excel.SchemeExcel;
import com.aqara.common.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class Scheme {
    @Autowired
    SchemeService SchemeService;

    private void SchemeData() {
        File file = new File("D:\\客户方案表_20230413160334.xlsx");
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
