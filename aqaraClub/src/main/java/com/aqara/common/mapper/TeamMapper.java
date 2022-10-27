package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Team;

@Repository
public interface TeamMapper {
	
	List<Team> select();

	void insert(Team Team);
	
	void update(Team Team);
	
	void delete(Integer id);

}
