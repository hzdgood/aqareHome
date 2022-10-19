package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.SpeedyTheme;

@Repository
public interface SpeedyThemeMapper {
	
	List<SpeedyTheme> select(String theme);
	
	void insert(SpeedyTheme SpeedyTheme);
	
	void update(SpeedyTheme SpeedyTheme);
	
	void delete(String id);
	
}
