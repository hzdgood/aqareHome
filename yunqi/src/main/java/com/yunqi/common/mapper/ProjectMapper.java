package com.yunqi.common.mapper;

import com.yunqi.common.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {
    List<Project> select(Project Project);

    List<Project> selectName(String name);

    List<Project> selectItemId(String itemId);

    void insert(Project project);

    void update(Project project);

    void delete(Integer id);
}
