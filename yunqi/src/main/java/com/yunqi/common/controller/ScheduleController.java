package com.yunqi.common.controller;

import com.yunqi.common.service.TechService;
import com.yunqi.common.view.*;
import com.yunqi.common.viewService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private TimeStService TimeStService;

    private WorkStService WorkStService;

    @Autowired
    public void setMapper(TimeStService TimeStService) {
        this.TimeStService = TimeStService;
    }

    @Autowired
    public void setMapper(WorkStService WorkStService) {
        this.WorkStService = WorkStService;
    }

    @CrossOrigin
    @RequestMapping("/time")
    public List<TimeSt> select(TimeSt TimeSt) {
        return TimeStService.select(TimeSt);
    }

    @CrossOrigin
    @RequestMapping("/work")
    public List<WorkSt> select(WorkSt WorkSt) {
        return WorkStService.select(WorkSt);
    }
}
