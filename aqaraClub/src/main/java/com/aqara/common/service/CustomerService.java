package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqara.common.mapper.CustomerMapper;
import com.aqara.common.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerMapper customerMapper;
	
	public List<Customer> select(String sales, String depart, String startTime, String endTime){
		return customerMapper.select(sales, depart ,startTime, endTime);
	}
	
	public List<Customer> currentData(){
		return customerMapper.currentData();
	}
	
	public void insert(Customer customer) {
		customerMapper.insert(customer);
	}
	
	public void delete(Integer id) {
		customerMapper.delete(id);
	}
	
	public void upload(Customer customer) {
		customerMapper.upload(customer);
	}
	
}
