package com.aqara.common.controller;

import com.aqara.common.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private SurveyService SurveyService;

    @Autowired
    public void setMapper(SurveyService SurveyService) {
        this.SurveyService = SurveyService;
    }
}
