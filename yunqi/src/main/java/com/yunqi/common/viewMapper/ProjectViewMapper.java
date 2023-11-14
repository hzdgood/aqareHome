package com.yunqi.common.viewMapper;

import com.yunqi.common.view.ProjectView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectViewMapper {
    List<ProjectView> select(ProjectView projectView);
    List<ProjectView> selectCom(ProjectView projectView);
}
