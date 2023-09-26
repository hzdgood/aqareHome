package com.yunqi.common.mapper;

import com.yunqi.common.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {
    List<Project> select();

    List<Project> selectId(Integer id);

    void insert(Project project);

    void update(Project project);
}
