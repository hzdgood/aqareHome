package com.yunqi.common.broadcast;

import com.yunqi.common.entity.Project;
import com.yunqi.common.excel.WorkExcel;
import com.yunqi.common.service.ProjectService;
import com.yunqi.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class WorkBoot {

    @Autowired
    private WorkSheetService WorkSheetService;

    @Autowired
    private ProjectService ProjectService;

    // @Scheduled(cron = "0 59 15 * * ?")
    private void uploadExcel() {
        File file = new File("D:\\download\\工单表.xlsx");
        try {
            List<Project> project = ProjectService.select();
            WorkExcel.workExcel(file, WorkSheetService, project);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
