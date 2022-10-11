package com.aqara.common.controller;

import com.aqara.common.entity.SpeedyContent;
import com.aqara.common.service.*;
import java.util.*;
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
	public Map<String, Object> select(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SpeedyContent> list = SpeedyContentService.select(id);
		map.put("pageNo", 1);
		map.put("pageSize", 10);
		map.put("totalCount", list.size());
		map.put("totalPage", list.size() / 10);
		map.put("data", list);
		return map;
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
