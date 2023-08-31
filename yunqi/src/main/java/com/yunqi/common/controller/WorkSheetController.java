package com.yunqi.common.controller;

import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workSheet")
public class WorkSheetController {
    private WorkSheetService WorkSheetService;

    @Autowired
    public void setMapper(WorkSheetService WorkSheetService) {
        this.WorkSheetService = WorkSheetService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    private void insert(WorkSheet WorkSheet) {
        WorkSheetService.insert(WorkSheet);
    }

    @CrossOrigin
    @RequestMapping("/update")
    private void update(WorkSheet WorkSheet) {
        WorkSheetService.update(WorkSheet);
    }

    @CrossOrigin
    @RequestMapping("/sign")
    private void sign(Integer id) {
        WorkSheetService.sign(id);
    }

    @CrossOrigin
    @RequestMapping("/write")
    private void write(Integer id) {
        WorkSheetService.write(id);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    private void delete(Integer id) {
        WorkSheetService.delete(id);
    }
}
