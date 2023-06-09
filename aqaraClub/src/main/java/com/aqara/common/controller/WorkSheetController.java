package com.aqara.common.controller;

import com.aqara.common.entity.WorkSheet;
import com.aqara.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void synchronize(WorkSheet WorkSheet) throws ParseException {
        String orderStr = WorkSheet.getOrderStr();
        String dateOfStr = WorkSheet.getDateOfStr();
        String completeStr = WorkSheet.getCompleteStr();
        String signInStr = WorkSheet.getSignInStr();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if (!"".equals(orderStr) && orderStr != null) {
            WorkSheet.setOrderDate(simpleDateFormat.parse(orderStr));
        }
        if (!"".equals(dateOfStr) && dateOfStr != null) {
            WorkSheet.setDateOfVisit(simpleDateFormat.parse(dateOfStr));
        }
        if (!"".equals(completeStr) && completeStr != null) {
            WorkSheet.setCompleteTime(simpleDateFormat.parse(completeStr));
        }
        if (!"".equals(signInStr) && signInStr != null) {
            WorkSheet.setSignInTime(simpleDateFormat.parse(signInStr));
        }
        String itemId = WorkSheet.getItemId();
        List<WorkSheet> list = WorkSheetService.selectId(itemId);
        if (list.size() == 0) {
            WorkSheetService.insert(WorkSheet);
        } else {
            WorkSheetService.update(WorkSheet);
            WorkSheetService.insert(WorkSheet);
        }
    }
}
