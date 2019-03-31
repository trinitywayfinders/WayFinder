package ie.tcd.wayfinder.userprefs.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ie.tcd.wayfinder.userprefs.service.UserPrefsService;
import ie.tcd.wayfinders.entities.User;
import ie.tcd.wayfinders.entities.UserPrefs;
import ie.tcd.wayfinders.repositories.UserPrefsRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserPrefsController {

//	@Autowired
//	private UserPrefsRepository repo;
	
	@Autowired
	private UserPrefsService userPrefsService;
	
//	@CrossOrigin(origins = "*")
//	@GetMapping(path="/api/getUserPrefs")
//    public UserPrefs getUserPrefs(@RequestBody Map<String, Object> request) {
//		if (!request.containsKey(UserPrefs.USERNAME_KEY)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "Username not provided");
//		}
//		String username = (String)request.get(UserPrefs.USERNAME_KEY);
//		UserPrefs userPrefs = userPrefsService.getUserPrefsByUsername(username);
//		if (userPrefs==null) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "User does not exist in DB.");
//		}
//		return userPrefs;
//    }
	
	@CrossOrigin(origins = "*")
	@GetMapping(path="/api/getUserPrefs")
	@PreAuthorize("hasAuthority('ROLE_USER')")
    public UserPrefs getUserPrefs(@AuthenticationPrincipal Principal principal) {		
		return userPrefsService.getUserPrefsByUsername(principal.getName());
    }
	
	

//	@CrossOrigin(origins = "*")
//	@PostMapping(path="/api/setUserPrefs")
//	public UserPrefs setUserPrefs(@RequestBody Map<String, Object> request) {
//		HashSet<Integer> vals = new HashSet<Integer>();
//		if (!request.containsKey(UserPrefs.USERNAME_KEY)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "Username not provided");
//		}
//		String username = (String)request.get(UserPrefs.USERNAME_KEY);
//		
//		if (repo.findByUsername(username)!=null) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "Username already exists in DB. Update preferences using updateUserPrefs endpoint");
//		}
//		
//		int concernHealth = 1;
//		if (request.containsKey(UserPrefs.HEALTH_KEY)){
//			concernHealth = (int) request.get(UserPrefs.HEALTH_KEY);
//			if (concernHealth<0 || concernHealth>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernHealth");
//			}
//		}
//		if(vals.contains(concernHealth)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernHealth has the same value as one other param");
//		}
//		vals.add(concernHealth);
//		
//		int concernSpeed = 2;
//		if (request.containsKey(UserPrefs.SPEED_KEY)){
//			concernSpeed = (int) request.get(UserPrefs.SPEED_KEY);
//			if (concernSpeed<0 || concernSpeed>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernSpeed");
//			}
//		}
//		if(vals.contains(concernSpeed)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernSpeed has the same value as one other param");
//		}
//		vals.add(concernSpeed);
//		
//		int concernCost = 3;
//		if (request.containsKey(UserPrefs.COST_KEY)){
//			concernCost = (int) request.get(UserPrefs.COST_KEY);
//			if (concernCost<0 || concernCost>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernCost");
//			}
//		}
//		if(vals.contains(concernCost)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernCost has the same value as one other param");
//		}
//		vals.add(concernCost);
//		
//		int concernPollutionAvoidance = 4;
//		if (request.containsKey(UserPrefs.POLLUTION_AVOIDANCE_KEY)){
//			concernPollutionAvoidance = (int) request.get(UserPrefs.POLLUTION_AVOIDANCE_KEY);
//			if (concernPollutionAvoidance<0 || concernPollutionAvoidance>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernPollutionAvoidance");
//			}
//		}
//		if(vals.contains(concernPollutionAvoidance)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernPollutionAvoidance has the same value as one other param");
//		}
//		vals.add(concernPollutionAvoidance);
//		
//		int concernEmissionsReduction = 5;
//		if (request.containsKey(UserPrefs.EMISSIONS_REDUCTION_KEY)){
//			concernEmissionsReduction = (int) request.get(UserPrefs.EMISSIONS_REDUCTION_KEY);
//			if (concernEmissionsReduction<0 || concernEmissionsReduction>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernEmissionsReduction");
//			}
//		}
//		if(vals.contains(concernEmissionsReduction)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernEmissionsReduction has the same value as one other param");
//		}
//		vals.add(concernEmissionsReduction);
//		
//		UserPrefs userPrefsObj = new UserPrefs(username,concernHealth,concernSpeed,concernCost,concernPollutionAvoidance,concernEmissionsReduction);
//		repo.save(userPrefsObj);
//		userPrefsObj = repo.findByUsername(username);
//		return userPrefsObj;
//    }
	
	@CrossOrigin(origins = "*")
	@PostMapping(path="/api/setUserPrefs")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public UserPrefs setUserPrefs(@AuthenticationPrincipal Principal principal, @RequestBody UserPrefs userPrefs) {
		return userPrefsService.setUserPrefsByUsername(principal.getName(), userPrefs);
    }
	
//	
//	@CrossOrigin(origins = "*")
//	@PostMapping(path="/api/updateUserPrefs")
//	public UserPrefs updateUserPrefs(@RequestBody Map<String, Object> request) {
//		HashSet<Integer> vals = new HashSet<Integer>();
//		
//		if (!request.containsKey(UserPrefs.USERNAME_KEY)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "Username not in request");
//		}
//		String username = (String)request.get(UserPrefs.USERNAME_KEY);
//		
//		UserPrefs userPrefsObj = repo.findByUsername(username);
//		if (userPrefsObj==null) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "User does not exist in DB. Set user preferences first using the setUserPrefs endpoint");
//		}
//		
//		int concernHealth = userPrefsObj.getConcernHealth();
//		if (request.containsKey(UserPrefs.HEALTH_KEY)){
//			concernHealth = (int) request.get(UserPrefs.HEALTH_KEY);
//			if (concernHealth<0 || concernHealth>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernHealth");
//			}
//		}
//		if (vals.contains(concernHealth)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernHealth has same value as one other param");
//		}
//		userPrefsObj.setConcernHealth(concernHealth);
//		vals.add(concernHealth);
//		
//		int concernSpeed = userPrefsObj.getConcernSpeed();
//		if (request.containsKey(UserPrefs.SPEED_KEY)){
//			concernSpeed = (int) request.get(UserPrefs.SPEED_KEY);
//			if (concernSpeed<0 || concernSpeed>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernSpeed");
//			}
//		}
//		if (vals.contains(concernSpeed)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernSpeed has same value as one other param");
//		}
//		userPrefsObj.setConcernSpeed(concernSpeed);
//		vals.add(concernSpeed);
//		
//		int concernCost = userPrefsObj.getConcernCost();
//		if (request.containsKey(UserPrefs.COST_KEY)){
//			concernCost = (int) request.get(UserPrefs.COST_KEY);
//			if (concernCost<0 || concernCost>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernCost");
//			}
//		}
//		if (vals.contains(concernCost)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernCost has same value as one other param");
//		}
//		userPrefsObj.setConcernCost(concernCost);
//		vals.add(concernCost);
//		
//		int concernPollutionAvoidance = userPrefsObj.getConcernPollutionAvoidance();
//		if (request.containsKey(UserPrefs.POLLUTION_AVOIDANCE_KEY)){
//			concernPollutionAvoidance = (int) request.get(UserPrefs.POLLUTION_AVOIDANCE_KEY);
//			if (concernPollutionAvoidance<0 || concernPollutionAvoidance>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernPollutionAvoidance");
//			}
//		}
//		if (vals.contains(concernPollutionAvoidance)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernPollutionAvoidance has same value as one other param");
//		}
//		userPrefsObj.setConcernPollutionAvoidance(concernPollutionAvoidance);
//		vals.add(concernPollutionAvoidance);
//		
//		int concernEmissionsReduction = userPrefsObj.getConcernEmissionsReduction();
//		if (request.containsKey(UserPrefs.EMISSIONS_REDUCTION_KEY)){
//			concernEmissionsReduction = (int) request.get(UserPrefs.EMISSIONS_REDUCTION_KEY);
//			if (concernEmissionsReduction<0 || concernEmissionsReduction>5) {
//				throw new ResponseStatusException(
//				           HttpStatus.BAD_REQUEST, "Invalid value for concernEmissionsReduction");
//			}
//		}
//		if (vals.contains(concernEmissionsReduction)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "concernEmissionsReduction has same value as one other param");
//		}
//		userPrefsObj.setConcernEmissionsReduction(concernEmissionsReduction);
//		vals.add(concernEmissionsReduction);
//		
//		repo.save(userPrefsObj);
//		userPrefsObj = repo.findByUsername(username);
//		return userPrefsObj;
//    }
//
//	@CrossOrigin(origins = "*")
//	@PostMapping(path="/api/removeUserPrefs")
//	public boolean removeUserPrefs(@RequestBody Map<String, Object> request) {
//		boolean status = false;
//		if (!request.containsKey(UserPrefs.USERNAME_KEY)) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "Username not in request");
//		}
//		String username = (String)request.get(UserPrefs.USERNAME_KEY);
//		
//		UserPrefs userPrefsObj = repo.findByUsername(username);
//		if (userPrefsObj==null) {
//			throw new ResponseStatusException(
//			           HttpStatus.BAD_REQUEST, "User does not exist in DB.");
//		}
//
//		try {
//			repo.delete(userPrefsObj);
//			status = true;
//		}
//		catch (Exception e) {
//		     status = false;
//		}
//		return status;
//    }
	
}
