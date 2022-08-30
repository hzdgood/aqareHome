package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Survey;

@Repository
public interface SurveyMapper  {
	
	List<Survey> select(String code, String startTime, String endTime);
	
	List<Survey> currentData();
	
	void insert(Survey survey);
	
	void upload(Survey survey);
	
	void delete(Integer id);
}
