package com.aqara.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Project;

@Repository
public interface ProjectMapper {

    List<Project> select(String code);

    List<Project> currentData();

    void insert(Project project);

    void delete();
}
