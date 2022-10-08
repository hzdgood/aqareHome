package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.SpeedyContent;

@Repository
public interface SpeedyContentMapper {
	
    List<SpeedyContent> select(Integer id);
	
	void insert(SpeedyContent SpeedyContent);
	
	void delete(Integer id);
}
