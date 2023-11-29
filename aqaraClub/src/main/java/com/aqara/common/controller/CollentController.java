package com.aqara.common.controller;

import com.aqara.common.entity.Collent;
import com.aqara.common.service.CollentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collent")
public class CollentController {
    private CollentService CollentService;

    @Autowired
    public void setMapper(CollentService CollentService) {
        this.CollentService = CollentService;
    }
}
