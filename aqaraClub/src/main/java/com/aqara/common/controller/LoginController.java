package com.aqara.common.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class LoginController {
	
	@CrossOrigin
   	@RequestMapping("/info")
    public String info() {
   		return "";
   	}
	
	@CrossOrigin
   	@RequestMapping("/logout")
    public String logout() {
   		return "";
   	}
	
	@CrossOrigin
   	@RequestMapping("/login")
    public String login() {
   		return "";
   	}
}
