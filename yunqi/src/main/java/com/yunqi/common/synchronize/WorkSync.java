package com.yunqi.common.synchronize;

import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sync")
public class WorkSync {

    @Autowired
    private WorkSheetService WorkSheetService;

    @CrossOrigin
    @RequestMapping("/workSheet")
    public void synchronize(WorkSheet WorkSheet) {
        List<WorkSheet> list = WorkSheetService.select(WorkSheet);
        if (list.isEmpty()) {
            WorkSheetService.insert(WorkSheet);
        } else {
            WorkSheetService.update(WorkSheet);
        }
    }
}
