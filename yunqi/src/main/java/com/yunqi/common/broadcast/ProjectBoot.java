package com.yunqi.common.broadcast;

import com.yunqi.common.entity.Sales;
import com.yunqi.common.entity.Tech;
import com.yunqi.common.excel.ProjectExcel;
import com.yunqi.common.service.ProjectService;
import com.yunqi.common.service.SalesService;
import com.yunqi.common.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class ProjectBoot {

    @Autowired
    private ProjectService ProjectService;

    @Autowired
    private SalesService SalesService;

    @Autowired
    private TechService TechService;

    //@Scheduled(cron = "0 14 14 * * ?")
    private void uploadExcel() {
        File file = new File("D:\\download\\项目汇总表921.xlsx");
        try {
            List<Tech> tech = TechService.select();
            List<Sales> sales = SalesService.select();
            ProjectExcel.ProjectExcel(file, ProjectService, tech, sales);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
