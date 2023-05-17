package com.aqara.common.controller;

import com.aqara.common.service.SchemeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class SchemeHistoryController {
    private SchemeHistoryService SchemeHistoryService;

    @Autowired
    public void setMapper(SchemeHistoryService SchemeHistoryService) {
        this.SchemeHistoryService = SchemeHistoryService;
    }
}
