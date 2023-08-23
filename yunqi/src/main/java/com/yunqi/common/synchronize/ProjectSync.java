package com.yunqi.common.synchronize;

import com.yunqi.common.entity.Project;
import com.yunqi.common.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/synchronize")
public class ProjectSync {
    private ProjectService ProjectService;

    @Autowired
    public void setMapper(ProjectService ProjectService) {
        this.ProjectService = ProjectService;
    }

    @CrossOrigin
    @RequestMapping("/project")
    public void synchronize(Project Project) {
        String code = Project.getCode();
        List<Project> list = ProjectService.select(code);
        if (list.size() == 0) {
            ProjectService.insert(Project);
        } else {
            ProjectService.update(Project);
            ProjectService.insert(Project);
        }
    }
}
