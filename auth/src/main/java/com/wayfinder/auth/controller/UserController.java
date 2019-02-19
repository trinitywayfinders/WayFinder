package com.wayfinder.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wayfinder.auth.entity.User;
import com.wayfinder.auth.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {	
	@Autowired
	private UserService userService;
	
	@GetMapping("/me")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Object getUser() {
    	OAuth2Authentication oauth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
    	
        return oauth2Authentication.getPrincipal();
	}
	
	@PostMapping("/")
	public @ResponseBody User registerNewUser(@RequestBody User newUser) {
		return userService.registerNewUser(newUser);
	}
}
