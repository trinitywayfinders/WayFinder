package com.wayfinder.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wayfinder.auth.service.UserService;
import ie.tcd.wayfinders.entities.User;

@CrossOrigin(origins = {"*"})

@RestController
@RequestMapping("/api/user")
public class UserController {	
	@Autowired
	private UserService userService;
	
	@GetMapping("/me")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public User getUser(@AuthenticationPrincipal User user) {    	
        return user;
	}
	
	@PostMapping("/")
	public @ResponseBody User registerNewUser(@RequestBody User newUser) {
		return userService.registerNewUser(newUser);
	}
}
