package com.yunqi.common.broadcast;

import com.yunqi.common.excel.ProductExcel;
import com.yunqi.common.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.FileNotFoundException;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class ProductBoot {

    @Autowired
    private ProductService ProductService;

    // @Scheduled(cron = "0 24 17 * * ?")
    private void uploadExcel() {
        File file = new File("D:\\download\\物料清单.xlsx");
        try {
            ProductExcel.productExcel(file, ProductService);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
