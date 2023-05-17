package com.aqara.common.mapper;

import com.aqara.common.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper {
    List<Customer> select(String sales, String depart, String startTime, String endTime);

    List<Customer> selectId(String itemId);

    List<Customer> currentData();

    void insert(Customer customer);

    void update(Customer customer);

    void delete(String itemId);

    void deleteAll(List<Integer> ids);

}
