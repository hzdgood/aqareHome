package com.aqara.common.controller;

import com.aqara.common.service.CollentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collent")
public class CollentController {

    @Autowired
    CollentService CollentService;

}
