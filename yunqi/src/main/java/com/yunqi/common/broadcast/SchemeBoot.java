package com.yunqi.common.broadcast;

import com.yunqi.common.entity.Product;
import com.yunqi.common.entity.Project;
import com.yunqi.common.entity.Scheme;
import com.yunqi.common.entity.SyncScheme;
import com.yunqi.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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

    @Autowired
    private SyncSchemeService SyncSchemeService;

    @Autowired
    private HistoryService HistoryService;

    @Scheduled(cron = "0 58 12 * * ?")
    private void s(){
        List<SyncScheme> SyncScheme = SyncSchemeService.select();
        for(SyncScheme sync: SyncScheme) {
            String itemId = sync.getItemId();
            List<Scheme> scheme = SchemeService.selectItem(itemId);
            if(scheme.isEmpty()) { // 如果找不到itemId 新增
                List<Project> Project = ProjectService.selectItem(sync.getProjectId());
                List<Product> Product = ProductService.selectItem(sync.getProductId());
                Scheme Scheme = new Scheme();
                if(!Project.isEmpty()){ // 查询产品 查询项目
                    Scheme.setProjectId(Project.get(0).getId());
                    if(!Product.isEmpty()){ // 查询产品
                        Scheme.setProductId(Product.get(0).getId());
                        Scheme.setItemId(itemId);
                        Scheme.setNumber(Integer.parseInt(sync.getNumber()));
                        Scheme.setNotSent(Integer.parseInt(sync.getNotSent()));
                        Scheme.setNotInstalled(Double.parseDouble(sync.getNotInstalled()));
                        Scheme.setUnregulated(Double.parseDouble(sync.getUnregulated()));
                        Scheme.setCreateName("导入数据");
                        SyncSchemeService.delete(sync.getId());
                        SchemeService.insert(Scheme); // 插入方案表
                        HistoryService.insert(Scheme); // 插入历史表
                    } else {
                        System.out.println("产品ID:" + sync.getProductId());
                    }
                } else {
                    System.out.println("项目ID:" + sync.getProjectId());
                }
            } else { // 如果有itemId 修改
//                Scheme Scheme = new Scheme();
//                Scheme.setId(scheme.get(0).getId());
//                Scheme.setNumber(Integer.parseInt(sync.getNumber()));
//                Scheme.setNotSent(Integer.parseInt(sync.getNotSent()));
//                double s = Double.parseDouble(sync.getNotInstalled())  - Scheme.getInstallNumber();
//                double t = Double.parseDouble(sync.getUnregulated())  - Scheme.getDebugNumber();
//                Scheme.setNotInstalled(Double.parseDouble(sync.getNotInstalled()));
//                Scheme.setUnregulated(Double.parseDouble(sync.getUnregulated()));
//                Scheme.setCreateName("更新数据");
//                SyncSchemeService.delete(sync.getId());
//                SchemeService.update(Scheme); // 修改方案表
//                HistoryService.update(Scheme); // 修改历史表
            }
        }
    }
}
