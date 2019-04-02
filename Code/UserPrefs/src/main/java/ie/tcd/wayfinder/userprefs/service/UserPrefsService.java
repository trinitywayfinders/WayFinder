package ie.tcd.wayfinder.userprefs.service;

import ie.tcd.wayfinders.entities.User;
import ie.tcd.wayfinders.entities.UserPrefs;

public interface UserPrefsService {
	UserPrefs getUserPrefs(User user);
	
	UserPrefs getUserPrefsByUsername(String username);
	
	UserPrefs setUserPrefs(User user, UserPrefs userPrefs);
	
	UserPrefs setUserPrefsByUsername(String username, UserPrefs userPrefs);
}