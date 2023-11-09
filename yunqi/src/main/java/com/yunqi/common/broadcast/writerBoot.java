package com.yunqi.common.broadcast;

import com.yunqi.common.entity.Scheme;
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
    private HistoryService HistoryService;

    @Autowired
    private WriterService WriterService;

    @Autowired
    private SchemeService SchemeService;

    // @Scheduled(cron = "0 56 16 * * ?")
    private void s(){
        List<Writer> w = WriterService.select();
        for(Writer Writer : w) {
            Integer schemeID = Writer.getSchemeId();
            List<Scheme> list = HistoryService.selectId(schemeID);
            if(!list.isEmpty()) {
                Scheme Scheme = new Scheme();
                Scheme s = list.get(0);
                double install = Writer.getInstall();
                double debug = Writer.getDebug();
                double installNumber = s.getInstallNumber();
                double debugNumber = s.getDebugNumber();
                double notInstalled = s.getNotInstalled();
                double unregulated = s.getUnregulated();
                Scheme.setId(s.getId());
                Scheme.setInstallNumber(installNumber + install);
                Scheme.setDebugNumber(debugNumber + debug);
                Scheme.setNotInstalled(notInstalled - install);
                Scheme.setUnregulated(unregulated - debug);
                Scheme.setCreateName("核销");
                WriterService.writerCom(Writer.getId()); // 设置查询无效
                SchemeService.updateId(Scheme);
                // HistoryService.updateId(Scheme); // 方案修改
            } else {
                System.out.println("方案ID:" + schemeID);
            }
        }
    }
}
