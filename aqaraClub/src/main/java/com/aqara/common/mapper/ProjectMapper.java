package com.aqara.common.mapper;

import com.aqara.common.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {

    List<Project> select(String code);

    List<Project> currentData();

    void insert(Project project);

    void deleteAll(List<Integer> ids);

    void delete(Integer id);
}
