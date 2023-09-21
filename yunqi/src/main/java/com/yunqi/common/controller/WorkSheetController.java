package com.yunqi.common.controller;

import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.service.WorkSheetService;
import com.yunqi.common.view.WriterView;
import com.yunqi.common.viewService.WriterViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workSheet")
public class WorkSheetController {
    private WorkSheetService WorkSheetService;

    private WriterViewService WriterViewService;

    @Autowired
    public void setMapper(WorkSheetService WorkSheetService) {
        this.WorkSheetService = WorkSheetService;
    }

    @Autowired
    public void setMapper(WriterViewService WriterViewService) {
        this.WriterViewService = WriterViewService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    private void insert(WorkSheet WorkSheet) {
        WorkSheetService.insert(WorkSheet);
    }

    @CrossOrigin
    @RequestMapping("/update") //核销完成
    private String update(WorkSheet WorkSheet) {
        WorkSheetService.update(WorkSheet);
        return "核销完成";
    }

    @CrossOrigin
    @RequestMapping("/updateInfo") //核销完成
    private String updateInfo(WorkSheet WorkSheet) {
        WorkSheetService.updateInfo(WorkSheet);
        return "修改完成";
    }

    @CrossOrigin
    @RequestMapping("/sign")
    private void sign(Integer id, String updateName) { WorkSheetService.sign(id, updateName); }

    @CrossOrigin
    @RequestMapping("/depart") // 离场
    private void depart(Integer id, String updateName) {
        WriterView WriterView = new WriterView();
        WriterView.setWorkId(id);
        WriterViewService.select(WriterView);

        WorkSheetService.depart(id, updateName);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    private void delete(Integer id, String updateName) {
        WorkSheetService.delete(id, updateName);
    }
}
