package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.aqara.common.entity.SpeedyTeam;
import com.aqara.common.mapper.SpeedyTeamMapper;

public class SpeedyTeamService {
	@Autowired
	SpeedyTeamMapper SpeedyTeamMapper;
	
	public List<SpeedyTeam> select(String type) {
		return SpeedyTeamMapper.select(type);
	}
	
	public void insert(SpeedyTeam SpeedyTeam) {
		SpeedyTeamMapper.insert(SpeedyTeam);
	}
	
	public void update(SpeedyTeam SpeedyTeam) {
		SpeedyTeamMapper.update(SpeedyTeam);
	}
	
	public void delete(String ids) {
		SpeedyTeamMapper.delete(ids);
	}
}
