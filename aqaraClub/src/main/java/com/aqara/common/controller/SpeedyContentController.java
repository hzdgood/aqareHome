package com.aqara.common.controller;

import com.aqara.common.entity.SpeedyContent;
import com.aqara.common.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speedy/content")
public class SpeedyContentController {
	
	@Autowired
	SpeedyContentService SpeedyContentService;
	
	@CrossOrigin
	@RequestMapping("/select")
	public List<SpeedyContent> select(Integer id) {
		return SpeedyContentService.select(id);
	}
	
	@CrossOrigin
	@RequestMapping("/insert")
	public void insert(SpeedyContent SpeedyContent) {
		SpeedyContentService.insert(SpeedyContent);
	}
	
	@CrossOrigin
	@RequestMapping("/delete")
	public void delete(Integer id) {
		SpeedyContentService.delete(id);
	}
}
