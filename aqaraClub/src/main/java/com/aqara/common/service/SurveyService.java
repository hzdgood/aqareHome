package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqara.common.entity.Survey;
import com.aqara.common.mapper.SurveyMapper;

@Service
public class SurveyService {
	
	@Autowired
	SurveyMapper surveyMapper;
	
	public List<Survey> select(String code, String startTime, String endTime){
		return surveyMapper.select(code, startTime, endTime);
	}
	
	public List<Survey> currentData(){
		return surveyMapper.currentData();
	}

	public void insert(Survey survey) {
		surveyMapper.insert(survey);
	}
	
	public void upload(Survey survey) {
		surveyMapper.upload(survey);
	}
	
	void delete(Integer id) {
		surveyMapper.delete(id);
	}
}
