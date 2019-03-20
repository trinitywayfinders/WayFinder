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

    private static double totalDistance = 5;
    
	@PostMapping(path="/api/route", consumes={ MediaType.ALL_VALUE })
	public ResponseEntity<String> retriveRoute(@RequestBody UserRouteRequest request) {
			
		logger.debug("Origin: " + request.getOrigin());
		logger.debug("Destination: " + request.getDestination());
		logger.debug("Mode: " + request.getMode().toString());

		UriComponents uri;
		HttpResponse response = getRoute(request);
		
		String responseBody = null;
		String responseBodySegment1 = null;
		String responseBodySegment2 = null;
		String responseBodySegment3 = null;
		
		try {
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			UserRouteRequest request1 = get1stSegment(3000, responseBody, request.getMode());
			UserRouteRequest request3 = get3rdSegment(3000, responseBody, request.getMode());

			HttpResponse responseSegment1 = getRoute(request1);
			responseBodySegment1 = EntityUtils.toString(responseSegment1.getEntity(), "UTF-8");
			
			String endSegment1 = getSegment1EndLatLng(responseBodySegment1);

			HttpResponse responseSegment3 = getRoute(request3);
			responseBodySegment3 = EntityUtils.toString(responseSegment3.getEntity(), "UTF-8");
			
			String startSegment3 = getSegment3StartLatLng(responseBodySegment3);
			
			UserRouteRequest request2 = get2ndSegment(endSegment1, startSegment3, TravelMode.driving);
			HttpResponse responseSegment2 = getRoute(request2);
			responseBodySegment2 = EntityUtils.toString(responseSegment2.getEntity(), "UTF-8");
			
		} catch (IOException | ParseException | NullPointerException e) {
			e.printStackTrace();
		}
		
		String x = combineRouteLegs(responseBodySegment1, responseBodySegment2, responseBodySegment3);
		
		return new ResponseEntity<String>(responseBodySegment3, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
	}
	
	public boolean checkTotalDistanceRoute(String originalJson) {
		
		JSONArray routes  = new JSONObject(originalJson).getJSONArray("routes");
		
		for(int i = 0; i < routes.length(); i++) {
			JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");
			
			JSONObject leg = legs.getJSONObject(0);
			int distance = leg.getJSONObject("distance").getInt("value");
			
			if(distance > totalDistance*1000)
				return true;
			}
		return false; 
		}
		
	public String getSegment1EndLatLng(String jsonResponse) {
		try {
			JSONArray routes  = new JSONObject(jsonResponse).getJSONArray("routes");
			
			JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
				
			Double lat = legs.getJSONObject(0).getJSONObject("end_location").getDouble("lat");
			Double lng = legs.getJSONObject(0).getJSONObject("end_location").getDouble("lng");
				
			return Double.toString(lat)+","+Double.toString(lng);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getSegment3StartLatLng(String jsonResponse) {
		try {
			JSONArray routes  = new JSONObject(jsonResponse).getJSONArray("routes");
			
			JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
				
			Double lat = legs.getJSONObject(0).getJSONObject("start_location").getDouble("lat");
			Double lng = legs.getJSONObject(0).getJSONObject("start_location").getDouble("lng");
					
			return Double.toString(lat)+","+Double.toString(lng);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public HttpResponse getRoute(UserRouteRequest route) {
		// Parameter check goes here
		MultiValueMap<java.lang.String,java.lang.String> params = route.toParams();
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
		
		return response;
	}
	
	
	/*
	 * 
	 * 0. Checks total distance of route. If journey > 6km split by distance
	 * 		params -> originalJson
	 * 		returns true/false
	 * 
	 * 0.1 If journey < 6km do nothing. 
	 * 
	 * 	Need 3 methods to calculate legs ^	
	 * 
	 * 
	 * 1. return 1st leg of journey (double Distance, originalJsonRoute) -> return userRouteRequest object (origin, destination (of 1st leg), mode)
	 * 2. return 3rd leg of journey (double Distance, originalJsonRoute) -> return userRouteRequest object (origin (of 3rd leg), destination (of 3rd leg), mode)
	 * 3. return 2nd leg of journey ( startLocation (end of 1st leg), endLocation (start of 3rd leg), originalJson) -> return userRouteRequest object (origin (of 2nd leg), destination (of 2nd leg), mode)
	 * 
	 * 
	 * 4. send UserRouteRequest to google  (userRouteRequest) -> returns jsonRoute
	 * 
	 * 5. Combine 3 legs 
	 */
	
	private UserRouteRequest getUserRouteRequest(JSONObject step1, JSONObject step2, TravelMode travelMode) {
		
		double originalLat = step1.getJSONObject("start_location").getDouble("lat");
		double originalLng = step1.getJSONObject("start_location").getDouble("lng");
	
		String originSegment1 = originalLat + "," + originalLng;
		
		double destinationSegment1Lat = step2.getJSONObject("start_location").getDouble("lat");
		double destinationSegment1Lng = step2.getJSONObject("start_location").getDouble("lng");
	
		String destinationSegment1 = destinationSegment1Lat + "," + destinationSegment1Lng;
		
		return new UserRouteRequest(originSegment1, destinationSegment1, travelMode);
		
	}
	
	public UserRouteRequest get1stSegment(double segment1Length, String response, TravelMode travelMode) {
		
		try {
			JSONArray routes  = new JSONObject(response).getJSONArray("routes");
			
			for(int i = 0; i < routes.length(); i++) {
				JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");
				
				for(int j = 0; j < legs.length(); j++) {
					JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");
								
					double totalDistance = 0.0;
					
					for(int k = 0; k < steps.length(); k++) {
						JSONObject currentStep = steps.getJSONObject(k);
						
						int distance = currentStep.getJSONObject("distance").getInt("value");
						totalDistance += distance;
				
						if(totalDistance > segment1Length) {
							JSONObject firstStep = steps.getJSONObject(0);						
							
							return getUserRouteRequest(firstStep, currentStep , travelMode);
						}
					}
				}
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
public UserRouteRequest get3rdSegment(double segment3Length, String response, TravelMode travelMode) {
		
		try {
			JSONArray routes  = new JSONObject(response).getJSONArray("routes");
			
			for(int i = 0; i < routes.length(); i++) {
				JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");
				
				for(int j = 0; j < legs.length(); j++) {
					JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");
								
					double totalDistance = 0.0;
					
					for(int k = steps.length()-1; k > 0; k--) {
						JSONObject currentStep = steps.getJSONObject(k);
						
						int distance = currentStep.getJSONObject("distance").getInt("value");
						totalDistance += distance;
				
						if(totalDistance > segment3Length) {
							JSONObject lastStep = steps.getJSONObject(steps.length()-1);						
							
							return getUserRouteRequest(currentStep, lastStep, travelMode);
						}
					}
				}
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public UserRouteRequest get2ndSegment(String startLatLng, String endLatLng, TravelMode travelMode) {
		return new UserRouteRequest(startLatLng, endLatLng, travelMode);
	}
	
	public String combineRouteLegs(String response1, String response2, String response3) {

		JSONArray routes1  = new JSONObject(response1).getJSONArray("routes");
		JSONArray legsArray1 = routes1.getJSONObject(0).getJSONArray("legs");
		
		JSONObject leg2 = extractLegFromResponse(response2);
		JSONObject leg3 = extractLegFromResponse(response3);

		legsArray1.put(leg2);
		legsArray1.put(leg3);

		routes1.getJSONObject(0).getJSONArray("legs").remove(0);
		routes1.getJSONObject(0).getJSONArray("legs").put(legsArray1);
		
		//THIS IS WHERE WE ARE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String yy = routes1.toString();
		
		return yy;
	}
	
	private JSONObject extractLegFromResponse(String response) {
		JSONArray routes  = new JSONObject(response).getJSONArray("routes");
		return routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0);
	}
	

	//ToDo: Consider trips shorter than 3-6km.
	// Split legs using steps instead of distance.
	/*public UserRouteRequest[] splitJourney(String response) {
		
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
						if(totalDistance > 3) {

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
	}*/
	
}
