package com.aqara.common.controller;

import com.aqara.common.entity.SpeedyTheme;
import com.aqara.common.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speedy/theme")
public class SpeedyThemeController {
	
	@Autowired
	SpeedyThemeService SpeedyThemeService;
	
	@CrossOrigin
	@RequestMapping("/select")
	public List<SpeedyTheme> select() {
		return SpeedyThemeService.select();
	}
	
	@CrossOrigin
	@RequestMapping("/insert")
	public void insert(SpeedyTheme SpeedyTheme) {
		SpeedyThemeService.insert(SpeedyTheme);
	}
	
	@CrossOrigin
	@RequestMapping("/delete")
	public void delete(Integer id) {
		SpeedyThemeService.delete(id);
	}
}
