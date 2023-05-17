package com.aqara.common.controller;

import com.aqara.common.entity.WorkSheet;
import com.aqara.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workSheet")
public class WorkSheetController {

    private WorkSheetService WorkSheetService;

    @Autowired
    public void setMapper(WorkSheetService WorkSheetService) {
        this.WorkSheetService = WorkSheetService;
    }

    @CrossOrigin
    @RequestMapping("/synchronize")
    public void synchronize(WorkSheet WorkSheet) {
        String code = WorkSheet.getItemId();
        List<WorkSheet> list = WorkSheetService.selectId(code);
        if (list.size() == 0) {
            WorkSheetService.insert(WorkSheet);
        } else {
            WorkSheetService.update(WorkSheet);
            WorkSheetService.insert(WorkSheet);
        }
    }
}
