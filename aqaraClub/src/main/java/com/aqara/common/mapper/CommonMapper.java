package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Common;

@Repository
public interface CommonMapper {
	
	List<Common> getValue(String name);
	
	void insert(Common Common);

}
