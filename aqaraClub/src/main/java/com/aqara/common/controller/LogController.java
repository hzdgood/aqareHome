package com.aqara.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aqara.common.entity.Logging;
import com.aqara.common.service.LoggingService;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LoggingService LoggingService;

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(String name, String custom, String info) {
        Logging log = new Logging();
        log.setName(name);
        log.setCustom(custom);
        log.setInfo(info);
        LoggingService.insert(log);
    }

    @CrossOrigin
    @RequestMapping("/select")
    public List<Logging> select(String name, String custom) {
        return LoggingService.select(name, custom);
    }
}
