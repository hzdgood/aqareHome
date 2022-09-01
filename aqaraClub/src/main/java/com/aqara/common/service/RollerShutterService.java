package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.RollerShutter;
import com.aqara.common.mapper.*;

@Service
public class RollerShutterService {
	
	@Autowired
	RollerShutterMapper RollerShutterMapper;
	
	public List<RollerShutter> currentData(){
		return RollerShutterMapper.currentData();
	}
	
	public void insert(RollerShutter RollerShutter) {
		RollerShutterMapper.insert(RollerShutter);
	}
}
