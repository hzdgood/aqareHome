package com.aqara.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aqara.common.entity.Login;

@Repository
public interface LoginMapper {
	
	List<Login> select(String username, String password);

	void insert(Login Login);
	
	void update(Login Login);
	
	void delete(Integer id);
}
