package com.wayfinder.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wayfinder.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
