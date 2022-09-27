package com.aqara.common.controller;

import com.aqara.common.entity.Survey;
import com.aqara.common.service.*;
import com.aqara.common.utils.ReadExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/survey")
public class SurveyController {
	
	@Autowired
	SurveyService SurveyService;
	
	@CrossOrigin
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) {
		List<Survey> list = ReadExcelUtil.SurveyExcel(file);
		for (int i = 0; i < list.size(); i++) {
			Survey survey = list.get(i);
			SurveyService.insert(survey);
		}
	}
}
