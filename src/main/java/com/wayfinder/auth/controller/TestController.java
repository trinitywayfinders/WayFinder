package com.wayfinder.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/test")
	public String test() {
		return "Hello World!";
	}
	
	@GetMapping("/user")
	public UserDetails user() {		
		return userDetailsService.loadUserByUsername("saad");
	}
}
