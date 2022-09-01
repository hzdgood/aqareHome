package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.OpenCurtain;
import com.aqara.common.mapper.*;

@Service
public class OpenCurtainService {
	
	@Autowired
	OpenCurtainMapper OpenCurtainMapper;
	
	public List<OpenCurtain> currentData(){
		return OpenCurtainMapper.currentData();
	}
	
	public void insert(OpenCurtain OpenCurtain) {
		OpenCurtainMapper.insert(OpenCurtain);
	}

}
