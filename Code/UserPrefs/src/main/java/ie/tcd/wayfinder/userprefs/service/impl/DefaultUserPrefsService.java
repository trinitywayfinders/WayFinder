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
	public UserPrefs setUserPrefs(User user, UserPrefs userPrefs) {
		user.setUserPrefs(userPrefs);
		
		User updatedUser = userRepository.save(user);
		
		return updatedUser.getUserPrefs();
	}

	@Override
	public UserPrefs getUserPrefsByUsername(String username) {
		User user = userRepository.findByUsername(username);
				
		if(user != null) {
			UserPrefs userPrefs = this.getUserPrefs(user);
			
			if(userPrefs != null) {
				System.out.println("User Prefs ID: " + userPrefs.getUserPrefsId());
			}
			
			return userPrefs;
		}
		
		return null;
	}

	@Override
	public UserPrefs setUserPrefsByUsername(String username, UserPrefs newUserPrefs) {
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			UserPrefs oldUserPrefs = user.getUserPrefs();
			return this.setUserPrefs(user, oldUserPrefs.update(newUserPrefs));
		}
		
		return null;
	}
}
