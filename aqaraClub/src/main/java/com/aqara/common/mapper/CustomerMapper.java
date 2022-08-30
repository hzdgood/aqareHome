package com.aqara.common.mapper;

import java.util.List;
import com.aqara.common.entity.Customer;

public interface CustomerMapper {
	
	List<Customer> select(String sales, String depart, String startTime, String endTime);
	
	List<Customer> currentData();
	
	void insert(Customer customer);
	
	void upload(Customer customer);
	
	void delete(Integer id);
}
