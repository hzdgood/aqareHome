package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.OpenCurtain;

@Repository
public interface OpenCurtainMapper {
	
	List<OpenCurtain> currentData();
	
	void insert(OpenCurtain OpenCurtain);
	
	void upload(OpenCurtain OpenCurtain);

}
