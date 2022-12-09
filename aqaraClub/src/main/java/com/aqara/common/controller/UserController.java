package com.aqara.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aqara.common.entity.Coordinate;
import com.aqara.common.entity.Huoban;
import com.aqara.common.entity.User;
import com.aqara.common.service.HuobanService;
import com.aqara.common.service.UserService;
import com.aqara.common.utils.CoordinateUtil;

@RestController
@RequestMapping("/user")
public class UserController {
 
    @Autowired
    private UserService userService;
    
    @Autowired
	HuobanService huobanService;
    
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
    
    @CrossOrigin
	@RequestMapping("/synchronize")
	public void synchronize() {
    	List<Huoban> list = huobanService.select();
		Huoban Huoban = list.get(list.size() - 1);
		String ticket = Huoban.getTicket();
		userService.delete();
    	userService.synchronize(ticket);
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
