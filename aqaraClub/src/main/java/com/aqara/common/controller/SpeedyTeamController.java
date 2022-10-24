package com.aqara.common.controller;

import com.aqara.common.entity.*;
import com.aqara.common.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/speedy/team")
public class SpeedyTeamController {
	
	@Autowired
	SpeedyTeamService SpeedyTeamService;
	
	@CrossOrigin
	@RequestMapping("/select")
	public Map<String, Object> select(@RequestBody PageReq PageReq) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SpeedyTeam> list = SpeedyTeamService.select(PageReq.getType());
		map.put("pageNo", 1);
		map.put("pageSize", 10);
		map.put("totalCount", list.size());
		map.put("totalPage", list.size() / 10);
		map.put("data", list);
		return map;
	}
	
	@CrossOrigin
	@RequestMapping("/insert")
	public void insert(SpeedyTeam SpeedyTeam) {
		SpeedyTeamService.insert(SpeedyTeam);
	}
	
	@CrossOrigin
	@RequestMapping("/update")
	public void update(SpeedyTeam SpeedyTeam) {
		SpeedyTeamService.update(SpeedyTeam);
	}
	
	@CrossOrigin
	@RequestMapping("/delete")
	public void delete(String ids) {
		List<Integer> list = new ArrayList<Integer>();
		if(ids.contains(",")) {
			String[] a = ids.split(",");
			for( int i=0; i<a.length; i++ ) {
				list.add(Integer.parseInt(a[i]));
			}
		} else {
			list.add(Integer.parseInt(ids));
		}
		SpeedyTeamService.delete(list);
	}

}
