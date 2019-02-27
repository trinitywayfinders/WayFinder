package com.wayfinder.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wayfinder.auth.entity.User;

public interface UserService extends UserDetailsService {
	User registerNewUser(User newUser);
}