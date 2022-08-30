package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.Project;
import com.aqara.common.mapper.ProjectMapper;

@Service
public class ProjectService {
	@Autowired
	ProjectMapper projectMapper;
	
	public List<Project> select(String code){
		return projectMapper.select(code);
	}
	
	public void insert(Project project) {
		projectMapper.insert(project);
	}
	
	public void upload(Project project) {
		projectMapper.upload(project);
	}
	
	public void delete(Integer id) {
		projectMapper.delete(id);
	}
}
