package com.yunqi.common.broadcast;

import com.yunqi.common.entity.Product;
import com.yunqi.common.entity.Project;
import com.yunqi.common.excel.SchemeExcel;
import com.yunqi.common.service.ProductService;
import com.yunqi.common.service.ProjectService;
import com.yunqi.common.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.List;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class SchemeBoot {

    @Autowired
    private SchemeService SchemeService;

    @Autowired
    private ProjectService ProjectService;

    @Autowired
    private ProductService ProductService;

    @Scheduled(cron = "0 28 18 * * ?")
    private void uploadExcel() {
        File file = new File("D:\\download\\客户方案.xlsx");
        //List<Project> Project = ProjectService.select();
        List<Product> Product = ProductService.select();
        //SchemeExcel.schemeToExcel(file, SchemeService, Project, Product);
    }
}
