package com.aqara.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aqara.common.entity.Coordinate;
import com.aqara.common.entity.User;
import com.aqara.common.service.UserService;
import com.aqara.common.utils.CoordinateUtil;

@RestController
@RequestMapping("/user")
public class UserController {
 
    @Autowired
    private UserService userService;
    
    @CrossOrigin
   	@RequestMapping("/select")
    public List<User> select(String username) {
   		return userService.select(username);
   	}
    
    @CrossOrigin
	@RequestMapping("/insert")
	public void insert(User user) {
		userService.insert(user);
	}
    
    /**
	 * 根据地址获取坐标
	 */
	@CrossOrigin
	@RequestMapping("/getCoordinate")
	public Coordinate getCoordinate(@RequestBody String address) {
		return CoordinateUtil.getCoordinate(address);
	}
}
