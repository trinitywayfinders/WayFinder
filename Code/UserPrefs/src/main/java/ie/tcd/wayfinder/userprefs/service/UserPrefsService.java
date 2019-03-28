package ie.tcd.wayfinder.userprefs.service;

import ie.tcd.wayfinders.entities.User;
import ie.tcd.wayfinders.entities.UserPrefs;

public interface UserPrefsService {
	UserPrefs getUserPrefs(User user);
	
	UserPrefs getUserPrefsByUsername(String username);
	
	void setUserPrefs(User user, UserPrefs userPrefs);
	
	void setUserPrefsByUsername(String username, UserPrefs userPrefs);
}