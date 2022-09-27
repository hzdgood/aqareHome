package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.Census;
import com.aqara.common.mapper.CensusMapper;

@Service
public class CensusService {
	@Autowired
	CensusMapper CensusMapper;
	
	public List<Census> select() {
		return CensusMapper.select();
	}
}
