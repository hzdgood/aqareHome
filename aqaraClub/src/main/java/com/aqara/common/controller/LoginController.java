package com.aqara.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aqara.common.entity.Login;
import com.aqara.common.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService LoginService;

    @CrossOrigin
    @RequestMapping("/select")
    public List<Login> select(String username, String password) {
        return LoginService.select(username, password);
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

    @CrossOrigin
    @RequestMapping("/delete")
    public void delete(Integer id) {
        LoginService.delete(id);
    }
}
