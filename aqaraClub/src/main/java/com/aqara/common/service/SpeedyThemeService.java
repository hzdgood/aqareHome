package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.SpeedyTheme;
import com.aqara.common.mapper.SpeedyThemeMapper;

@Service
public class SpeedyThemeService {
	
	@Autowired
	SpeedyThemeMapper SpeedyThemeMapper;
	
	public List<SpeedyTheme> select(String theme, String company) {
		return SpeedyThemeMapper.select(theme, company);
	}
	
	public void insert(SpeedyTheme SpeedyTheme) {
		SpeedyThemeMapper.insert(SpeedyTheme);
	}
	
	public void update(SpeedyTheme SpeedyTheme) {
		SpeedyThemeMapper.update(SpeedyTheme);
	}
	
	public void delete(String ids) {
		SpeedyThemeMapper.delete(ids);
	}

}
