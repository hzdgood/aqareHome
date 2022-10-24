package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.SpeedyContent;
import com.aqara.common.mapper.SpeedyContentMapper;

@Service
public class SpeedyContentService {
	
	@Autowired
	SpeedyContentMapper SpeedyContentMapper;
	
	public List<SpeedyContent> select(String theme) {
		return SpeedyContentMapper.select(theme);
	}
	
	public void insert(SpeedyContent SpeedyContent) {
		SpeedyContentMapper.insert(SpeedyContent);
	}
	
	public void update(SpeedyContent SpeedyContent) {
		SpeedyContentMapper.update(SpeedyContent);
	}
	
	public void delete(List<Integer> ids) {
		SpeedyContentMapper.delete(ids);
	}
}
