package com.yunqi.common.controller;

import com.yunqi.common.entity.Tech;
import com.yunqi.common.service.TechService;
import com.yunqi.common.view.ProjectView;
import com.yunqi.common.view.WorkView;
import com.yunqi.common.viewService.ProjectViewService;
import com.yunqi.common.viewService.WorkViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/view")
public class ViewController {
    private ProjectViewService ProjectViewService;
    private WorkViewService WorkViewService;
    private TechService TechService;
    @Autowired
    public void setMapper(TechService TechService) {
        this.TechService = TechService;
    }
    @Autowired
    public void setMapper(ProjectViewService ProjectViewService) {
        this.ProjectViewService = ProjectViewService;
    }
    @Autowired
    public void setMapper(WorkViewService WorkViewService) {
        this.WorkViewService = WorkViewService;
    }
    @CrossOrigin
    @RequestMapping("/project")
    public List<ProjectView> select(ProjectView ProjectView) {
        return ProjectViewService.select(ProjectView);
    }
    @CrossOrigin
    @RequestMapping("/work")
    public List<WorkView> select(WorkView WorkView) {
        List<Tech> list = TechService.select();
        List<WorkView> workList = WorkViewService.select(WorkView);
        for (WorkView workView : workList) {
            String techIds = workView.getTechID();
            workView.setTechName(getTechName(techIds, list));
        }
        return workList;
    }

    public String getTechName(String techIds, List<Tech> list) {
        StringBuilder str = new StringBuilder();
        if(techIds.contains(",") ){
            String[] arr = techIds.split(",");
            for (String techId : arr) {
                for (Tech tech : list) {
                    String id = tech.getId() + "";
                    if(techId.equals(id)) {
                        str.append(tech.getName()).append(",");
                    }
                }
            }
            if(str.toString().contains(",") ){
                str = new StringBuilder(str.substring(0, str.length() - 1));
            }
        } else {
            for (Tech tech : list) {
                String id = tech.getId() + "";
                if(techIds.equals(id)) {
                    System.out.println("111");
                    str.append(tech.getName());
                }
            }
        }
        return str.toString();
    }
}
