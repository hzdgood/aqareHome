package com.yunqi.common.controller;

import com.yunqi.common.entity.Scheme;
import com.yunqi.common.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scheme")
public class SchemeController {
    private SchemeService SchemeService;

    @Autowired
    public void setMapper(SchemeService SchemeService) {
        this.SchemeService = SchemeService;
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Scheme Scheme) {
        SchemeService.insert(Scheme);
    }
}

