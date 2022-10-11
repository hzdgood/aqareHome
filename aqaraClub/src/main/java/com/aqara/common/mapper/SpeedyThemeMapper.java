package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.SpeedyTheme;

@Repository
public interface SpeedyThemeMapper {
	
	List<SpeedyTheme> select();
	
	void insert(SpeedyTheme SpeedyTheme);
	
	void delete(Integer id);
}