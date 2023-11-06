package com.yunqi.common.controller;

import com.yunqi.common.entity.Writer;
import com.yunqi.common.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/writer")
public class WriterController {
    private WriterService WriterService;

    @Autowired
    public void setMapper(WriterService WriterService) {
        this.WriterService = WriterService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    private void insert(Writer Writer) { //新增核销单
        Integer id = Writer.getSchemeId();
        double install = Writer.getInstall(); //本次安装
        double debug = Writer.getDebug(); //本次调试
        WriterService.insert(Writer);
//        List<Scheme> list = SchemeService.selectId(id);
//        if(!list.isEmpty()) {
//            Scheme s = list.get(0);
//            double installNumber = s.getInstallNumber();
//            double debugNumber = s.getDebugNumber();
//            double notInstalled = s.getNotInstalled();
//            double unregulated = s.getUnregulated();
//            Scheme Scheme = new Scheme();
//            Scheme.setId(id);
//            Scheme.setInstallNumber(installNumber + install);
//            Scheme.setDebugNumber(debugNumber + debug);
//            Scheme.setNotInstalled(notInstalled - install);
//            Scheme.setUnregulated(unregulated - debug);
//            Scheme.setCreateName("核销");
//            SchemeService.update(Scheme); // 方案修改
//        }
    }
}
