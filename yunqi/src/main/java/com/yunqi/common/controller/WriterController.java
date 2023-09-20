package com.yunqi.common.controller;

import com.yunqi.common.entity.*;
import com.yunqi.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {
    private WriterService WriterService;
    private SchemeService SchemeService;

    @Autowired
    public void setMapper(WriterService WriterService) {
        this.WriterService = WriterService;
    }
    @Autowired
    public void setMapper(SchemeService SchemeService) {
        this.SchemeService = SchemeService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    private void insert(Writer Writer) {
        Integer id = Writer.getSchemeId();
        Integer install = Integer.valueOf(Writer.getInstall()); //本次安装
        Integer debug = Integer.valueOf(Writer.getDebug()); //本次调试

        List<Scheme> list = SchemeService.selectId(id);
        Scheme s = list.get(0);

        Integer installNumber = s.getInstallNumber();
        Integer debugNumber = s.getDebugNumber();
        Integer notInstalled = s.getNotInstalled();
        Integer unregulated = s.getUnregulated();

        Scheme Scheme = new Scheme();
        Scheme.setId(id);
        Scheme.setInstallNumber(installNumber + install);
        Scheme.setDebugNumber(debugNumber + debug);
        Scheme.setNotInstalled(notInstalled - install);
        Scheme.setUnregulated(unregulated - debug);

        SchemeService.update(Scheme); // 方案修改
        WriterService.insert(Writer); // 核销新增
    }
    @CrossOrigin
    @RequestMapping("/update")
    private void update(Writer Writer) {
        WriterService.update(Writer);
    }
}
