package ie.tcd.wayfind.lowlevel.controller;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import ie.tcd.wayfind.lowlevel.request.UserRouteRequest;
import ie.tcd.wayfind.lowlevel.type.TravelMode;
import ie.tcd.wayfinders.restLibrary.Library;

@EnableWebMvc
@RestController
public class LowLevelRouteController {
    private static final Logger logger = LoggerFactory.getLogger(LowLevelRouteController.class);
    private static final String endpoint = "https://maps.googleapis.com/maps/api/directions/json";
    private static String apiKey = "AIzaSyB2NHLaqVDF0uSmuNBMXI3DVsUanzdRD7Q";
    private static double segment1Distance = 3;
    
	@PostMapping(path="/api/route", consumes={ MediaType.ALL_VALUE })
	public ResponseEntity<String> retriveRoute(@RequestBody UserRouteRequest request) {
			
		logger.debug("Origin: " + request.getOrigin());
		logger.debug("Destination: " + request.getDestination());
		logger.debug("Mode: " + request.getMode().toString());
		
		// Parameter check goes here
		MultiValueMap<java.lang.String,java.lang.String> params = request.toParams();
		params.add("key", apiKey);
		
		UriComponents uri;
		HttpResponse response = null;
		
		try {
			UriComponentsBuilder.newInstance();
			uri = UriComponentsBuilder.fromUriString(endpoint).queryParams(params).build();
			String uriString = uri.toString();
			String uriEncoded = URIUtil.encodeQuery(uriString, "UTF-8");
			response = Library.GET(uriEncoded, Optional.empty());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String responseBody = null;
		
		try {
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			
			UserRouteRequest[] splitJourney = splitJourney(responseBody);
			UserRouteRequest request1 = splitJourney[0];
			
			MultiValueMap<java.lang.String,java.lang.String> params1 = request1.toParams();
			params1.add("key", apiKey);
			
			UriComponentsBuilder.newInstance();
			uri = UriComponentsBuilder.fromUriString(endpoint).queryParams(params1).build();
			String uriString = uri.toString();
			String uriEncoded = URIUtil.encodeQuery(uriString, "UTF-8");
			response = Library.GET(uriEncoded, Optional.empty());
			
			
			
			
		} catch (IOException | ParseException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(responseBody, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
	}
	
	
	
	
	public UserRouteRequest[] splitJourney(String response) {
		
		System.out.println("IN SPLIT JOURNEY......");
		try {
			JSONArray routes  = new JSONObject(response).getJSONArray("routes");
			
			for(int i = 0; i < routes.length(); i++) {
				JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");
				
				for(int j = 0; j < legs.length(); j++) {
					JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");
					
					
					double totalDistance = 0.0;
					
					for(int k = 0; k < steps.length(); k++) {
						JSONObject step = steps.getJSONObject(k);
						
						String[] distance = step.getJSONObject("distance").getString("text").split(" ");
						double currentDistance = Double.parseDouble(distance[0]);
						
						//Distance is given as km or m -> convert all to km
						if(distance[1].equals("m")) {
							currentDistance = currentDistance / 1000;
						}
						
						totalDistance += currentDistance;
						
						//after first "segment1Distance" km split the route
						if(totalDistance > segment1Distance) {

							//Get lat/lng of first step
							JSONObject firstStep = steps.getJSONObject(0);	
							double originalLat = firstStep.getJSONObject("start_location").getDouble("lat");
							double originalLng = firstStep.getJSONObject("start_location").getDouble("lng");
						
							String originSegment1 = originalLat + "," + originalLng;
							
							//Get lat/lng of final step
							JSONObject lastStep = legs.getJSONObject(0);	
							double destinationLat = lastStep.getJSONObject("end_location").getDouble("lat");
							double destinationLng = lastStep.getJSONObject("end_location").getDouble("lng");
						
							String destinationSegment2 = destinationLat + "," + destinationLng;
						
							//Get lat/lng of "segment1Distance" km away from origin
							double destinationSegment1Lat = step.getJSONObject("start_location").getDouble("lat");
							double destinationSegment1Lng = step.getJSONObject("start_location").getDouble("lng");
						
							String destinationSegment1 = destinationSegment1Lat + "," + destinationSegment1Lng;
							
							//Get new routes with different travel types
							UserRouteRequest segment1 = new UserRouteRequest( originSegment1, destinationSegment1, TravelMode.walking);
							UserRouteRequest segment2 = new UserRouteRequest( destinationSegment1, destinationSegment2,  TravelMode.walking);
						
							return new UserRouteRequest[] {segment1, segment2};
						}				
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR:"+e.getMessage());
		}
		return null;
	}
	
}
