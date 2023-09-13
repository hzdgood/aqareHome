package com.yunqi.common.controller;

import com.yunqi.common.entity.Project;
import com.yunqi.common.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService ProjectService;

    @Autowired
    public void setMapper(ProjectService ProjectService) {
        this.ProjectService = ProjectService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Project Project) {
        ProjectService.insert(Project);
    }

    @CrossOrigin
    @RequestMapping("/sysnc")
    public void sysnc(Project Project) {

    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(Project Project) {
        ProjectService.update(Project);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    public void delete(Integer id) {
        ProjectService.delete(id);
    }
}
