package com.aqara.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqara.common.entity.Scheme;
import com.aqara.common.mapper.SchemeMapper;

@Service
public class SchemeService {
	@Autowired
	SchemeMapper schemeMapper;
	
	public void insert(Scheme scheme) {
		schemeMapper.insert(scheme);
	}
}
