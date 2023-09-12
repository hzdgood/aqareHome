package com.yunqi.common.broadcast;

import com.yunqi.common.entity.Tech;
import com.yunqi.common.service.ProjectService;
import com.yunqi.common.service.TechService;
import com.yunqi.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class WorkSheetBoot {

    @Autowired
    private WorkSheetService WorkSheetService;

    @Autowired
    private ProjectService ProjectService;

    @Autowired
    private TechService TechService;

    @Scheduled(cron = "0 06 15 * * ?")
    private void uploadExcel() {
        File file = new File("D:\\download\\工单.xlsx");
        //List<Project> Project = ProjectService.select();
        List<Tech> Tech = TechService.select();
        //WorkSheetExcel.WorkSheetToExcel(file, WorkSheetService, Project, Tech);
    }
}
