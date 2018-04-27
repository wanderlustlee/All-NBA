package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.UserServiceImpl;


public class test {
	@Autowired
	UserServiceImpl userService;
	public boolean checkUsername(String username) {
		return userService.checkUsername(username);
	}
	
}
