package com.wayfinder.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ie.tcd.wayfinders.entities.User;

public interface UserService extends UserDetailsService {
	User registerNewUser(User newUser);
}