package com.yunqi.common.synchronize;

import com.yunqi.common.entity.WorkTime;
import com.yunqi.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class TimeSync {
    @Autowired
    private WorkSheetService WorkSheetService;

    @CrossOrigin
    @RequestMapping("/WorkTime")
    public void synchronize(WorkTime workTime) {


    }
}
