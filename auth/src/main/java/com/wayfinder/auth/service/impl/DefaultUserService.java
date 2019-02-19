package com.wayfinder.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wayfinder.auth.entity.User;
import com.wayfinder.auth.repository.UserRepository;
import com.wayfinder.auth.service.UserService;

@Service
public class DefaultUserService implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		if((user = userRepository.findByUsername(username)) == null) {
			throw new UsernameNotFoundException("username \"" + username + "\" not found" );
		}
		return user;
	}

	@Override
	public User registerNewUser(User newUser) {
		
		User user = userRepository.save(newUser);
		
		return user;
	}
}
