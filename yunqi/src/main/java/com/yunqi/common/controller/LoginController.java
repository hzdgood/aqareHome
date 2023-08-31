package com.yunqi.common.controller;

import com.yunqi.common.entity.Login;
import com.yunqi.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    private LoginService LoginService;
    @Autowired
    public void setMapper(LoginService LoginService) {
        this.LoginService = LoginService;
    }
    @CrossOrigin
    @RequestMapping("/select")
    public List<Login> select(Login Login) {
        return LoginService.select(Login);
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Login Login) {
        LoginService.insert(Login);
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(Login Login) {
        LoginService.update(Login);
    }

}
