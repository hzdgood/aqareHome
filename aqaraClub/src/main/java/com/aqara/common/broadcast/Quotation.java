package com.aqara.common.broadcast;

import com.aqara.common.excel.QuotationExcel;
import com.aqara.common.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@Configuration // 标记配置类
@EnableScheduling // 开启定时任务
public class Quotation {

    @Autowired
    QuotationService QuotationService;

    private void QuotationData() {
        File file = new File("D:\\收款表_20230506171943.xlsx");
        if (file.exists()) {
            System.out.print("OK");
            try {
                QuotationExcel.QuotationExcel(file, QuotationService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
