package com.yunqi.common.controller;

import com.yunqi.common.entity.WorkTime;
import com.yunqi.common.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workTime")
public class WorkTimeController {
    private WorkTimeService WorkTimeService;

    @Autowired
    public void setMapper(WorkTimeService WorkTimeService) {
        this.WorkTimeService = WorkTimeService;
    }

    @CrossOrigin
    @RequestMapping("/update") //
    private String update(WorkTime WorkTime) {
        WorkTimeService.update(WorkTime);
        return "签到成功";
    }

    @CrossOrigin
    @RequestMapping("/sign") // 签到
    private String sign(WorkTime WorkTime) {
        WorkTimeService.sign(WorkTime);
        return "签到成功";
    }

    @CrossOrigin
    @RequestMapping("/depart") // 离开
    private String depart(WorkTime WorkTime) {
        WorkTimeService.depart(WorkTime);
        return "离开成功";
    }
}
