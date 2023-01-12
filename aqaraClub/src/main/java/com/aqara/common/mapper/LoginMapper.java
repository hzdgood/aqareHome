package com.aqara.common.mapper;

import com.aqara.common.entity.Login;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginMapper {

    List<Login> select(String username, String password);

    void insert(Login Login);

    void update(Login Login);

    void delete(Integer id);
}
