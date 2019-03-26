package ie.tcd.wayfinder.userprefs.controller;
import java.util.Map;

import ie.tcd.wayfinders.entities.UserPrefs;
import ie.tcd.wayfinders.repositories.UserPrefsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPrefsController {

	@Autowired
	private UserPrefsRepository repo;
	
	@CrossOrigin(origins = "*")
	@GetMapping(path="/api/getUserPrefs")
    public UserPrefs getUserPrefs(@RequestBody Map<String, Object> request) {
    	//Get object from database, convert to POJO and return it
		//UserPrefs userPrefsObj = new UserPrefs("saad",(short)1,(short)1,(short)1,(short)1,(short)1);
		if (!request.containsKey("username")) {
			return null;
		}
		String username = (String)request.get("username");
		// UserPrefs userPrefsObj = repo.findByUsername(username);
		// return userPrefsObj;
		return null;
    }

	@CrossOrigin(origins = "*")
	@PostMapping(path="/api/setUserPrefs")
	public UserPrefs setUserPrefs(@RequestBody Map<String, Object> request) {
//    	//Get current user prefs from DB and conver to POJO
//		UserPrefs userPrefsObj = new UserPrefs("saad",(short)1,(short)1,(short)1,(short)1,(short)1);
//		//
//		repo.save(userPrefsObj);
//		if (request.containsKey("concernHealth")){
//			userPrefsObj.setConcernHealth(Short.parseShort((String) request.get("concernHealth")));
//		}
//		if (request.containsKey("concernSpeed")){
//			userPrefsObj.setConcernSpeed(Short.parseShort((String) request.get("concernSpeed")));
//		}
//		if (request.containsKey("concernCost")){
//			userPrefsObj.setConcernCost(Short.parseShort((String) request.get("concernCost")));
//		}
//		if (request.containsKey("concernPollutionAvoidance")){
//			userPrefsObj.setConcernPollutionAvoidance(Short.parseShort((String) request.get("concernPollutionAvoidance")));
//		}
//		if (request.containsKey("concernEmissionsReduction")){
//			userPrefsObj.setConcernEmissionsReduction(Short.parseShort((String) request.get("concernEmissionsReduction")));
//		}
//		return userPrefsObj;
		return null;
    }
    
}
