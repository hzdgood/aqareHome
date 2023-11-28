package com.yunqi.common.synchronize;

import com.yunqi.common.entity.*;
import com.yunqi.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sync")
public class WorkSync {
    @Autowired
    private ProjectService ProjectService;

    @Autowired
    private WorkSheetService WorkSheetService;

    @Autowired
    private WorkTimeService WorkTimeService;

    @CrossOrigin
    @RequestMapping("/workSheet")
    public void synchronize(WorkSheet WorkSheet) {
        WorkSheet Work = new WorkSheet();
        List<Project> Project = ProjectService.selectItem(WorkSheet.getProjectItem());
        if (!Project.isEmpty()) {
            Work.setType(WorkSheet.getType()); // 类型
            Work.setDateOfVisit(WorkSheet.getDateOfVisit()); // 上门时间
            Work.setTechIds(WorkSheet.getTechIds()); // 上门人员
            Work.setProjectId(Project.get(0).getId()); // 项目ID
            Work.setProjectItem(WorkSheet.getProjectItem()); // 项目item
            Work.setItemId(WorkSheet.getItemId()); // 工单item
            Work.setDisabled(WorkSheet.getDisabled()); // 有效
            Work.setStandardTime(WorkSheet.getStandardTime()); // 额定工时
            WorkSheetService.insert(Work);
        } else {
            System.out.printf(WorkSheet.getProjectItem());
        }
    }

    private void insertSimpleWork(WorkSheet WorkSheet) {
        WorkTime WorkTime = new WorkTime();
        String techIds = WorkSheet.getTechIds();
        String[] techs = techIds.split("、");
        if (techs.length > 0) {
            for (String tech : techs) {
                WorkTime.setProjectId(WorkSheet.getProjectId());
                WorkTime.setStatus("待上门");
                WorkTime.setTechId(tech);
                WorkTime.setCreateName(WorkSheet.getCreateName());
                WorkTime.setWorkId(WorkSheet.getId());
                WorkTimeService.insert(WorkTime);
            }
        } else {
            WorkTime.setProjectId(WorkSheet.getProjectId());
            WorkTime.setStatus("待上门");
            WorkTime.setTechId(WorkSheet.getTechIds());
            WorkTime.setCreateName(WorkSheet.getCreateName());
            WorkTime.setWorkId(WorkSheet.getId());
            WorkTimeService.insert(WorkTime);
        }
    }
}
