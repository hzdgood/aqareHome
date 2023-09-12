package com.yunqi.common.controller;

import com.yunqi.common.view.ProjectView;
import com.yunqi.common.view.WorkView;
import com.yunqi.common.viewService.ProjectViewService;
import com.yunqi.common.viewService.WorkViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/view")
public class ViewController {
    private ProjectViewService ProjectViewService;
    private WorkViewService WorkViewService;

    @Autowired
    public void setMapper(ProjectViewService ProjectViewService) {
        this.ProjectViewService = ProjectViewService;
    }

    @CrossOrigin
    @RequestMapping("/project")
    public List<ProjectView> select(ProjectView ProjectView) {
        return ProjectViewService.select(ProjectView);
    }

    @Autowired
    public void setMapper(WorkViewService WorkViewService) {
        this.WorkViewService = WorkViewService;
    }

    @CrossOrigin
    @RequestMapping("/work")
    public List<WorkView> select(WorkView WorkView) {
        return WorkViewService.select(WorkView);
    }
}
