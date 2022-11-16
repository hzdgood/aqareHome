package com.aqara.common.service;

import java.util.List;
import com.aqara.common.entity.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.SpeedyTheme;
import com.aqara.common.mapper.SpeedyThemeMapper;

@Service
public class SpeedyThemeService {
	
	@Autowired
	SpeedyThemeMapper SpeedyThemeMapper;
	
	public List<SpeedyTheme> select(PageReq PageReq) {
		if(PageReq.getTeamId() == null || PageReq.getTeamId().equals("null")){
			return SpeedyThemeMapper.select(null, PageReq.getTheme(), PageReq.getType());
		} else {
			return SpeedyThemeMapper.select(Integer.parseInt(PageReq.getTeamId()), PageReq.getTheme(), PageReq.getType());
		}
	}
	
	public void insert(SpeedyTheme SpeedyTheme) {
		SpeedyThemeMapper.insert(SpeedyTheme);
	}
	
	public void update(SpeedyTheme SpeedyTheme) {
		SpeedyThemeMapper.update(SpeedyTheme);
	}
	
	public void delete(List<Integer> ids) {
		SpeedyThemeMapper.delete(ids);
	}

}
