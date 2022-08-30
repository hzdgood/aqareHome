package com.aqara.common.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.aqara.common.entity.Project;
import com.aqara.common.service.ProjectService;
import com.aqara.common.utils.ReadExcelUtil;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectService ProjectService;
	
	@CrossOrigin
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) {
		List<Project> list = ReadExcelUtil.projectExcel(file);
		for (int i = 0; i < list.size(); i++) {
			Project Project = list.get(i);
			ProjectService.upload(Project);
		}
	}
}
