package com.aqara.common.broadcast;

import com.aqara.common.excel.SchemeHistoryExcel;
import com.aqara.common.service.SchemeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class SchemeHistoryBoot {
    @Autowired
    SchemeHistoryService SchemeHistoryService;

    private void SchemeHistoryData() {
        File file = new File("D:\\收款表_20230506171943.xlsx");
        if (file.exists()) {
            System.out.print("OK");
            try {
                SchemeHistoryExcel.SchemeHistoryExcel(file, SchemeHistoryService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
