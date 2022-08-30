package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqara.common.entity.Collent;
import com.aqara.common.mapper.CollentMapper;

@Service
public class CollentService {
	
	@Autowired
	CollentMapper collentMapper;
	
	public List<Collent> select(String code, String startTime, String endTime){
		return collentMapper.select(code, startTime, endTime);
	}
	
	public List<Collent> currentData(){
		return collentMapper.currentData();
	}
	
	public void insert(Collent collent) {
		collentMapper.insert(collent);
	}
	
	public void upload(Collent collent) {
		collentMapper.upload(collent);
	}
	
	public void delete(Integer id) {
		collentMapper.delete(id);
	}
}
