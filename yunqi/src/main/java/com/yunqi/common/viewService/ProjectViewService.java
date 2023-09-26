package com.yunqi.common.viewService;

import com.yunqi.common.view.ProjectView;
import com.yunqi.common.viewMapper.ProjectViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectViewService {
    private ProjectViewMapper ProjectViewMapper;

    @Autowired
    public void setMapper(ProjectViewMapper ProjectViewMapper) {
        this.ProjectViewMapper = ProjectViewMapper;
    }

    public List<ProjectView> select(ProjectView ProjectView) {
        return ProjectViewMapper.select(ProjectView);
    }
}
