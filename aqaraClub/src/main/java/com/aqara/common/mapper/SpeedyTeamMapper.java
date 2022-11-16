package com.aqara.common.mapper;

import java.util.List;
import com.aqara.common.entity.PageReq;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.SpeedyTeam;

@Repository
public interface SpeedyTeamMapper {
	
	List<SpeedyTeam> select(String type, String name);
	
	void insert(SpeedyTeam SpeedyTheme);
	
	void update(SpeedyTeam SpeedyTheme);
	
	void delete(List<Integer> ids);

}
