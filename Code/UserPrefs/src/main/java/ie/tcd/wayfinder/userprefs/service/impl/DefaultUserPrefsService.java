package ie.tcd.wayfinder.userprefs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.tcd.wayfinder.userprefs.service.UserPrefsService;
import ie.tcd.wayfinders.entities.User;
import ie.tcd.wayfinders.entities.UserPrefs;
import ie.tcd.wayfinders.repositories.UserRepository;

@Service
public class DefaultUserPrefsService implements UserPrefsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserPrefs getUserPrefs(User user) {
		return user.getUserPrefs();
	}
	
	@Override
	public void setUserPrefs(User user, UserPrefs userPrefs) {
		user.setUserPrefs(userPrefs);
		userRepository.save(user);
	}

	@Override
	public UserPrefs getUserPrefsByUsername(String username) {
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			return this.getUserPrefs(user);
		}
		
		return null;
	}

	@Override
	public void setUserPrefsByUsername(String username, UserPrefs userPrefs) {
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			this.setUserPrefs(user, userPrefs);;
		}
	}
}
