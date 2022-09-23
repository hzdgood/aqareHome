package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.User;

@Repository
public interface UserMapper {

	void insert(User user);
	
	List<User> select(String username);
	
	List<User> selectCode(String code);
	
	void delete();
}
