package ie.tcd.wayfind.lowlevel.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import ie.tcd.wayfind.lowlevel.type.TravelMode;
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
	
	public UserPreferences(String username, String url) {
		
		UserPreferences temp = getUserPreferences(username, url);
		
		ConcernHealth = temp.ConcernHealth;
		ConcernSpeed = temp.ConcernSpeed;
		ConcernCost = temp.ConcernCost;
		ConcernPollutionAvoidance = temp.ConcernPollutionAvoidance;
		ConcernEmissionsReduction = temp.ConcernEmissionsReduction;
		Username = temp.Username;
	}

	private UserPreferences getUserPreferences(String username, String baseUrl) {
		
		String url = baseUrl + "/api/getUserPrefs";
		
		//call API        
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
               
        HttpResponse response;
		try {
			
		    //create ObjectMapper instance
		    
		    RequestBody requestBody = new RequestBody(username);
		        
		    String jsonRequestContent = new ObjectMapper().writeValueAsString(requestBody);
		    response = Library.POST(url, Optional.of(headers), Optional.of(jsonRequestContent));
			
	        HttpEntity responseEntity = response.getEntity();
	        String responseString = EntityUtils.toString(responseEntity);
	        System.out.println(responseString);
	        JSONObject jsonResponse = new JSONObject(responseString);
	        
	        
	        //if user doesnt exist get default prefs.
	        if(response.getStatusLine().getStatusCode() == 404) {
	        	
	        	requestBody = new RequestBody("default");
		        jsonRequestContent = new ObjectMapper().writeValueAsString(requestBody);
	        	
	        	Library.POST(url, Optional.of(headers), Optional.of(jsonRequestContent));
	        }

	        int concernCost = jsonResponse.getInt("concernCost");
	        int concernSpeed = jsonResponse.getInt("concernSpeed");
	        int concernHealth = jsonResponse.getInt("concernHealth");
	        int concernPollutionAvoidance = jsonResponse.getInt("concernPollutionAvoidance");
	        int concernEmissionsReduction = jsonResponse.getInt("concernEmissionsReduction");
	        String responseUsername = username;
	        
	        if(!responseUsername.equals(username)) {
	        	return null;
	        }
	        
	        return new UserPreferences(concernHealth, concernSpeed, concernCost, concernPollutionAvoidance, concernEmissionsReduction, responseUsername);
	        
		//create+return object
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	
	private class RequestBody{

		@JsonProperty("username")
		String username;
		

		@JsonCreator
		private RequestBody(String username) {
			this.username = username;
		}
	}
	
}
