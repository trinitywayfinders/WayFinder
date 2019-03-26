package com.wayfinder.userprefs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserPrefsRepository extends CrudRepository<UserPrefs, Long> {
	
	@Query("select p from user_prefs where username=(:username)")
	UserPrefs findByUsername(@Param("username") String username);
}