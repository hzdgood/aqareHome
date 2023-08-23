package com.yunqi.common.synchronize;

import com.yunqi.common.entity.WorkSheet;
import com.yunqi.common.service.WorkSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/synchronize")
public class WorkSheetSync {
    private WorkSheetService WorkSheetService;

    @Autowired
    public void setMapper(WorkSheetService WorkSheetService) {
        this.WorkSheetService = WorkSheetService;
    }

    @CrossOrigin
    @RequestMapping("/worksheet")
    public void synchronize(WorkSheet WorkSheet) throws ParseException {
        String orderStr = WorkSheet.getOrderStr();
        String dateOfStr = WorkSheet.getDateOfStr();
        String completeStr = WorkSheet.getCompleteStr();
        String signInStr = WorkSheet.getSignInStr();
        String CompleteImage = WorkSheet.getCompleteImage();
        String signInImage = WorkSheet.getSignInImage();
        String handSignIn = WorkSheet.getHandSignIn();
        String createStr = WorkSheet.getCreateStr();
        String updateStr = WorkSheet.getUpdateStr();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        if (objectNull(orderStr)) {
            WorkSheet.setOrderDate(simpleDateFormat.parse(orderStr));
        }
        if (objectNull(dateOfStr)) {
            WorkSheet.setDateOfVisit(simpleDateFormat.parse(dateOfStr));
        }
        if (objectNull(completeStr)) {
            WorkSheet.setCompleteTime(simpleDateFormat.parse(completeStr));
        }
        if (objectNull(signInStr)) {
            WorkSheet.setSignInTime(simpleDateFormat.parse(signInStr));
        }
        if (objectNull(createStr)) {
            WorkSheet.setCompleteTime(simpleDateFormat.parse(createStr));
        }
        if (objectNull(updateStr)) {
            WorkSheet.setUpdateTime(simpleDateFormat.parse(updateStr));
        }

        if (!objectNull(completeStr) && objectNull(CompleteImage)) {
            WorkSheet.setCompleteTime(new Date());
            WorkSheet.setWorkStatus("已完成");
        }
        if (!objectNull(signInStr) && objectNull(signInImage)) {
            WorkSheet.setSignInTime(new Date());
            WorkSheet.setWorkStatus("待核销");
        }
        if (!objectNull(signInStr) && objectNull(handSignIn)) {
            WorkSheet.setSignInTime(new Date());
            WorkSheet.setWorkStatus("待核销");
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

    public boolean objectNull(String str) {
        return str != null && !str.equals("");
    }
}
