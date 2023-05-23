package com.aqara.common.broadcast;

import com.aqara.common.excel.ProductExcel;
import com.aqara.common.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class Product {

    @Autowired
    ProductService ProductService;

    // @Scheduled(cron = "0 53 16 * * ?")
    private void productData() {
        File file = new File("D:\\物料清单_20230411151439.xlsx");
        if (file.exists()) {
            System.out.print("OK");
            try {
                ProductExcel.productExcel(file, ProductService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
