package com.yunqi.common.synchronize;

import com.yunqi.common.entity.WorkTime;
import com.yunqi.common.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sync")
public class TimeSync {
    @Autowired
    private WorkTimeService WorkTimeService;

    @CrossOrigin
    @RequestMapping("/workTime")
    public void synchronize(WorkTime WorkTime) {
        List<WorkTime> list = WorkTimeService.select(WorkTime);
        if (list.isEmpty()) {
            WorkTimeService.insert(WorkTime);
        } else {
            WorkTimeService.update(WorkTime);
        }
    }
}
