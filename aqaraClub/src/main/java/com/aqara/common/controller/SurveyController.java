package com.aqara.common.controller;

import com.aqara.common.entity.Survey;
import com.aqara.common.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private SurveyService SurveyService;

    @Autowired
    public void setMapper(SurveyService SurveyService) {
        this.SurveyService = SurveyService;
    }

    @CrossOrigin
    @RequestMapping("/synchronize")
    public void synchronize(Survey Survey) {
        String code = Survey.getCode();
        List<Survey> list = SurveyService.selectId(code);
        if (list.size() == 0) {
            SurveyService.insert(Survey);
        } else {
            SurveyService.update(Survey);
            SurveyService.insert(Survey);
        }
    }
}
