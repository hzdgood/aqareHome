package com.yunqi.common.service;

import com.yunqi.common.entity.Login;
import com.yunqi.common.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private LoginMapper LoginMapper;

    @Autowired
    public void setMapper(LoginMapper LoginMapper) {
        this.LoginMapper = LoginMapper;
    }

    public List<Login> select(Login Login) {
        return LoginMapper.select(Login);
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
