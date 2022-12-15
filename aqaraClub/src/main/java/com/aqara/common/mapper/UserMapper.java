package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.User;

@Repository
public interface UserMapper {
	List<User> select(String userName, String engName);
	
	List<User> selectCode(String code);

	void update(User user);

	void insert(User user);
	
	void delete();
}
