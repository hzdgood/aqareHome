package com.yunqi.common.service;

import com.yunqi.common.entity.Project;
import com.yunqi.common.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectMapper projectMapper;

    @Autowired
    public void setMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public List<Project> select() {
        return projectMapper.select();
    }

    public List<Project> selectId(Integer id) {
        return projectMapper.selectId(id);
    }

    public void insert(Project project) {
        projectMapper.insert(project);
    }

    public void update(Project project) {
        projectMapper.update(project);
    }

    public List<Project> selectItem(String itemId) {
        return projectMapper.selectItem(itemId);
    }
}
