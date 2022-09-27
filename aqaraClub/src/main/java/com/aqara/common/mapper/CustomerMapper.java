package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Customer;

@Repository
public interface CustomerMapper {
	
	List<Customer> select(String sales, String depart, String startTime, String endTime);
	
	List<Customer> currentData();
	
	void insert(Customer customer);
	
	void delete(Integer id);
}
