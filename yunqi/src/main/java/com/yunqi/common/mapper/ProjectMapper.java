package com.yunqi.common.mapper;

import com.yunqi.common.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {
    List<Project> select();

    void insert(Project project);

    void update(Project project);

    void delete(Integer id);
}
