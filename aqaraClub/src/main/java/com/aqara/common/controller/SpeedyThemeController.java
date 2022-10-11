package com.aqara.common.controller;

import com.aqara.common.entity.SpeedyTheme;
import com.aqara.common.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> select() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SpeedyTheme> list = SpeedyThemeService.select();
		map.put("pageNo", 1);
		map.put("pageSize", 10);
		map.put("totalCount", list.size());
		map.put("totalPage", list.size() / 10);
		map.put("data", list);
		return map;
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
