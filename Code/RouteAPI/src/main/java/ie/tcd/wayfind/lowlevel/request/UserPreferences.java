package ie.tcd.wayfind.lowlevel.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.tcd.wayfinders.restLibrary.Library;

public class UserPreferences {
	
	public int ConcernHealth;
	public int ConcernSpeed;
	public int ConcernCost;
	public int ConcernPollutionAvoidance;
	public int ConcernEmissionsReduction;
	public String Username;
	
	public UserPreferences(int concernHealth, int concernSpeed, int concernCost, int concernPollutionAvoidance, int concernEmissionsReduction, String username) {
		super();
		ConcernHealth = concernHealth;
		ConcernSpeed = concernSpeed;
		ConcernCost = concernCost;
		ConcernPollutionAvoidance = concernPollutionAvoidance;
		ConcernEmissionsReduction = concernEmissionsReduction;
		Username = username;
	}
	
	public UserPreferences(String username) {
		
		UserPreferences temp = getUserPreferences(username);
		
		ConcernHealth = temp.ConcernHealth;
		ConcernSpeed = temp.ConcernSpeed;
		ConcernCost = temp.ConcernCost;
		ConcernPollutionAvoidance = temp.ConcernPollutionAvoidance;
		ConcernEmissionsReduction = temp.ConcernEmissionsReduction;
		Username = temp.Username;
	}

	private UserPreferences getUserPreferences(String username) {
		
		//call API        
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
       
        HttpResponse response;
		try {
			
		    //create ObjectMapper instance
		    ObjectMapper objectMapper = new ObjectMapper();
		
			response = Library.GET("http://localhost:3000/body", Optional.of(headers));
			
	        HttpEntity responseEntity = response.getEntity();
	        String responseString = EntityUtils.toString(responseEntity);
	        JSONObject jsonResponse = new JSONObject(responseString);

	        int concernCost = jsonResponse.getInt("concernCost");
	        int concernSpeed = jsonResponse.getInt("concernSpeed");
	        int concernHealth = jsonResponse.getInt("concernHealth");
	        int concernPollutionAvoidance = jsonResponse.getInt("concernPollutionAvoidance");
	        int concernEmissionsReduction = jsonResponse.getInt("concernEmissionsReduction");
	        String responseUsername = jsonResponse.getString("username");
	        
	        if(!responseUsername.equals(username)) {
	        	return null;
	        }
	        
	        UserPreferences x = new UserPreferences(concernHealth, concernSpeed, concernCost, concernPollutionAvoidance, concernEmissionsReduction, responseUsername); 
	        return x;
	        
		//create+return object
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
}
