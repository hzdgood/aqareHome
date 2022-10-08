package com.aqara.common.controller;

import com.aqara.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speedy/content")
public class SpeedyContentController {
	
	
	@Autowired
	SpeedyContentService SpeedyContentService;

}
