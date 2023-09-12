package com.yunqi.common.mapper;

import com.yunqi.common.view.ProjectView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectViewMapper {
    List<ProjectView> select(ProjectView projectView);
}
