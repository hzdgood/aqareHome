package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.User;
import com.aqara.common.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public void insert(User user) {
		userMapper.insert(user);
	}
	
	public List<User> select(String username) {
		return userMapper.select(username);
	}
}

