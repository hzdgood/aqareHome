package com.aqara.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aqara.common.entity.Login;
import com.aqara.common.mapper.LoginMapper;

@Service
public class LoginService {
	
	@Autowired
	LoginMapper LoginMapper;
	
	public List<Login> select(String username, String password){
		return LoginMapper.select(username, password);
	}
	
	public void insert(Login Login) {
		LoginMapper.insert(Login);
	}
	
	public void update(Login Login) {
		LoginMapper.update(Login);
	}
	
	public void delete(Integer id) {
		LoginMapper.delete(id);
	}
}
