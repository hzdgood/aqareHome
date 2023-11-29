package com.aqara.common.controller;

import com.aqara.common.entity.Huoban;
import com.aqara.common.entity.PageReq;
import com.aqara.common.entity.User;
import com.aqara.common.service.HuobanService;
import com.aqara.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private HuobanService huobanService;
    private UserService userService;

    @Autowired
    public void setMapper(HuobanService huobanService, UserService userService) {
        this.userService = userService;
        this.huobanService = huobanService;
    }

    @CrossOrigin
    @RequestMapping("/select")
    public List<User> select(String username, String engName) {
        return userService.select(username, engName);
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(User user) {
        userService.insert(user);
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(User user) {
        userService.update(user);
    }
}
