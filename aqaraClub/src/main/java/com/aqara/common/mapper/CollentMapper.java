package com.aqara.common.mapper;

import java.util.List;
import com.aqara.common.entity.Collent;

public interface CollentMapper {
	
	List<Collent> select(String code, String startTime, String endTime);
	
	List<Collent> currentData();
	
	void insert(Collent collent);
	
	void upload(Collent Collent);
	
	void delete(Integer id);

}
