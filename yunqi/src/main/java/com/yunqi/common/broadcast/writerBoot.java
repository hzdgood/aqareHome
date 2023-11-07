package com.yunqi.common.broadcast;

import com.yunqi.common.entity.Writer;
import com.yunqi.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class writerBoot {

    @Autowired
    private SchemeService SchemeService;

    @Autowired
    private WriterService WriterService;

    @Scheduled(cron = "0 45 12 * * ?")
    private void s(){
        List<Writer> w = WriterService.select();
        for(Writer Writer : w) {

        }
    }
}
