package com.aqara.common.mapper;

import com.aqara.common.entity.Survey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyMapper {

    List<Survey> select(String code, String startTime, String endTime);

    List<Survey> currentData();

    void insert(Survey survey);

    void delete(Integer id);

    void deleteAll(List<Integer> ids);
}
