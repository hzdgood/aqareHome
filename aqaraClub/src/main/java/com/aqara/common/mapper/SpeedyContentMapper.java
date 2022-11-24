package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.SpeedyContent;

@Repository
public interface SpeedyContentMapper {
	
    List<SpeedyContent> select(Integer themeId, String team, String theme, String type, String person);
	
	void insert(SpeedyContent SpeedyContent);
	
	void update(SpeedyContent SpeedyContent);
	
	void delete(List<Integer> ids);
}
