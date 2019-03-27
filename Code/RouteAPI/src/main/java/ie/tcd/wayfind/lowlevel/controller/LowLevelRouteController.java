package ie.tcd.wayfind.lowlevel.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import ie.tcd.wayfind.lowlevel.request.LatLng;
import ie.tcd.wayfind.lowlevel.request.UserRouteRequest;
import ie.tcd.wayfind.lowlevel.type.TravelMode;
import ie.tcd.wayfinders.restLibrary.Library;

@EnableWebMvc
@RestController
public class LowLevelRouteController {
	private static final Logger logger = LoggerFactory.getLogger(LowLevelRouteController.class);

	@Value("${routing.google.endpoint}")
	private String endpoint;

	@Value("${routing.google.apiKey}")
	private String apiKey;

	private static double totalDistance = 5;

	@PostMapping(path = "/api/route/", consumes = { MediaType.ALL_VALUE })
	public ResponseEntity<String> retriveRoute(@RequestBody UserRouteRequest request) {

		//Get user data from User-Preferences API + add preferences
		System.out.println("\n\n\nIN ROUTING API"+request.getOrigin());
		
		logger.debug("Origin: %s", request.getOrigin());
		logger.debug("Destination: %s", request.getDestination());
		logger.debug("Mode: %s", request.getMode().toString());

		HttpResponse response = getRoute(request, false);

		String responseBody = null;
		String responseBodySegment1 = null;
		String responseBodySegment2 = null;
		String responseBodySegment3 = null;

		try {
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			UserRouteRequest request1 = get1stSegment(2500, responseBody, request.getMode());
			UserRouteRequest request3 = get3rdSegment(2500, responseBody, request.getMode());

			HttpResponse responseSegment1 = getRoute(request1, false);
			responseBodySegment1 = EntityUtils.toString(responseSegment1.getEntity(), "UTF-8");

			String endSegment1 = getSegment1EndLatLng(responseBodySegment1);

			HttpResponse responseSegment3 = getRoute(request3, false);
			responseBodySegment3 = EntityUtils.toString(responseSegment3.getEntity(), "UTF-8");

			String startSegment3 = getSegment3StartLatLng(responseBodySegment3);

			UserRouteRequest request2 = get2ndSegment(endSegment1, startSegment3, TravelMode.driving);
			HttpResponse responseSegment2 = getRoute(request2, false);
			responseBodySegment2 = EntityUtils.toString(responseSegment2.getEntity(), "UTF-8");

		} catch (IOException | ParseException | NullPointerException e) {
			e.printStackTrace();
		}

		String combinedRoute = combineRouteLegs(responseBodySegment1, responseBodySegment2, responseBodySegment3);

		return new ResponseEntity<String>(combinedRoute, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
	}

	public boolean checkTotalDistanceRoute(String originalJson) {

		JSONArray routes = new JSONObject(originalJson).getJSONArray("routes");

		for (int i = 0; i < routes.length(); i++) {
			JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");

			JSONObject leg = legs.getJSONObject(0);
			int distance = leg.getJSONObject("distance").getInt("value");

			if (distance > totalDistance * 1000)
				return true;
		}
		return false;
	}

	public String getSegment1EndLatLng(String jsonResponse) {
		try {
			JSONArray routes = new JSONObject(jsonResponse).getJSONArray("routes");

			JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");

			Double lat = legs.getJSONObject(0).getJSONObject("end_location").getDouble("lat");
			Double lng = legs.getJSONObject(0).getJSONObject("end_location").getDouble("lng");

			return Double.toString(lat) + "," + Double.toString(lng);
		} catch (Exception e) {
			logger.error("getSegment1EndLatLng Error: " + e.getMessage());
			return null;
		}
	}

	public String getSegment3StartLatLng(String jsonResponse) {
		try {
			JSONArray routes = new JSONObject(jsonResponse).getJSONArray("routes");

			JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");

			Double lat = legs.getJSONObject(0).getJSONObject("start_location").getDouble("lat");
			Double lng = legs.getJSONObject(0).getJSONObject("start_location").getDouble("lng");

			return Double.toString(lat) + "," + Double.toString(lng);
		} catch (Exception e) {
			logger.error("getSegment3StartLatLng Error: " + e.getMessage());
			return null;
		}
	}

	public HttpResponse getRoute(UserRouteRequest route, boolean alternatives) {
		MultiValueMap<java.lang.String, java.lang.String> params = route.toParams();
		params.add("key", apiKey);
		
		if(alternatives) {
			params.add("alternatives", "true");
			params.add("avoid", "highways");
		}
		UriComponents uri;
		HttpResponse response = null;

		try {
			UriComponentsBuilder.newInstance();
			uri = UriComponentsBuilder.fromUriString(endpoint).queryParams(params).build();
			String uriString = uri.toString();
			String uriEncoded = URIUtil.encodeQuery(uriString, "UTF-8");
			response = Library.GET(uriEncoded, Optional.empty());
		} catch (IOException e) {
			logger.error("getRoute Error: " + e.getMessage());
		}

		return response;
	}

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
			JSONArray routes = new JSONObject(response).getJSONArray("routes");

			for (int i = 0; i < routes.length(); i++) {
				JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");

				for (int j = 0; j < legs.length(); j++) {
					JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");

					double totalDistance = 0.0;

					for (int k = 0; k < steps.length(); k++) {
						JSONObject currentStep = steps.getJSONObject(k);

						int distance = currentStep.getJSONObject("distance").getInt("value");
						totalDistance += distance;

						if (totalDistance > segment1Length) {
							JSONObject firstStep = steps.getJSONObject(0);

							return getUserRouteRequest(firstStep, currentStep, travelMode);
						}
					}
				}
			}
		} catch (JSONException e) {
			logger.error("get1stSegment Error: " + e.getMessage());
		}
		return null;
	}

	public UserRouteRequest get3rdSegment(double segment3Length, String response, TravelMode travelMode) {

		try {
			JSONArray routes = new JSONObject(response).getJSONArray("routes");

			for (int i = 0; i < routes.length(); i++) {
				JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");

				for (int j = 0; j < legs.length(); j++) {
					JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");

					double totalDistance = 0.0;

					for (int k = steps.length() - 1; k > 0; k--) {
						JSONObject currentStep = steps.getJSONObject(k);

						int distance = currentStep.getJSONObject("distance").getInt("value");
						totalDistance += distance;

						if (totalDistance > segment3Length) {
							JSONObject lastStep = steps.getJSONObject(steps.length() - 1);

							return getUserRouteRequest(currentStep, lastStep, travelMode);
						}
					}
				}
			}
		} catch (JSONException e) {
			logger.error("get3rdSegment Error: " + e.getMessage());
		}
		return null;
	}

	public UserRouteRequest get2ndSegment(String startLatLng, String endLatLng, TravelMode travelMode) {
		return new UserRouteRequest(startLatLng, endLatLng, travelMode);
	}

	public String combineRouteLegs(String response1, String response2, String response3) {

		JSONObject response1Json = new JSONObject(response1);

		JSONArray routes1 = response1Json.getJSONArray("routes");

		JSONArray legsArray1 = routes1.getJSONObject(0).getJSONArray("legs");
		JSONObject leg2 = extractLegFromResponse(response2);
		JSONObject leg3 = extractLegFromResponse(response3);

		legsArray1.put(leg2);
		legsArray1.put(leg3);

		return response1Json.toString();
	}

	private JSONObject extractLegFromResponse(String response) {
		JSONArray routes = new JSONObject(response).getJSONArray("routes");
		return routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0);
	}

	/*
	 * 1. New controller endpoint. /api/route/avoid/{lat}/{lng} 2. check if given
	 * latlng is on polyline for each step 3. If true -> get start and end location
	 * of current step get alternate routes check if given routes contain latlng If
	 * false -> check time for alternate route Replace current step with shortest
	 * alternate route
	 * 
	 * Solution 2: 
	 * 1. New controller endpoint. /api/route/avoid/{lat}/{lng} 
	 * 2. Get
	 * new route from starting location to block location 
	 * 3. Get starting location
	 * of last step of new route 
	 * 4. Iterate through all steps in original route 
	 * 		Find step which has same starting location 
	 * 		Return end location of that step. 
	 * 5. Get alternate routes from blockedStep start -> blockedStep end 
	 * 6. For alternate routes compare distance/time with original step -> return all possible alternate routes. 
	 * 7. For all alternate routes -> return one with shortest (/longest?) distance/time. 
	 * 8. Replace step in original route with shortest alternate route.
	 */

	@PostMapping(path="/api/route/avoid/{lat}/{lng}/", consumes={ MediaType.ALL_VALUE })
	public ResponseEntity<String> retriveRouteWithBlock(@RequestBody UserRouteRequest request, @PathVariable String lat, @PathVariable String lng) {
		
		String avoidLatLng = lat+","+lng;


		UserRouteRequest originalRoute = new UserRouteRequest(request.getOrigin(), request.getDestination(), request.getMode());
		UserRouteRequest segment1ToBlock = new UserRouteRequest(request.getOrigin(), avoidLatLng, request.getMode());
		
		HttpResponse originalResponse = getRoute(originalRoute, false);
		HttpResponse responseToBlock = getRoute(segment1ToBlock, false);

		HttpResponse responseToBlockSegmentStart = null;
		HttpResponse responseFromBlockSegmentEnd = null;
		
		String originalResponseBody = null;
		String responseBodyWithBlock = null;
		String responseBodyWithAlternatives = null;
		String responseBodyToBlockSegmentStart = null;
		String responseBodyFromBlockSegmentEnd = null;
		String finalRouteWithoutBlock = null;
		
				
		try {
			responseBodyWithBlock = EntityUtils.toString(responseToBlock.getEntity(), "UTF-8");
			LatLng blockSegmentStart = getStartLatLngBlockSegment(responseBodyWithBlock);
			System.out.println("Block segment start: "+ blockSegmentStart.Lat + "  " + blockSegmentStart.Lng);

			originalResponseBody = EntityUtils.toString(originalResponse.getEntity(), "UTF-8");
			LatLng blockSegmentEnd = getEndLatLngBlockSegment(originalResponseBody, blockSegmentStart.Lat, blockSegmentStart.Lng);
			
			if(blockSegmentEnd == null) {
				return new ResponseEntity<String>(originalResponseBody, HttpStatus.valueOf(originalResponse.getStatusLine().getStatusCode()));
			}
			
			System.out.println("Block segment end: "+ blockSegmentEnd.Lat + "  " + blockSegmentEnd.Lng);
			
			
			//Route from startBlock to endBlock with alternatives
			UserRouteRequest alternativeRoute = new UserRouteRequest(blockSegmentStart.toString(), blockSegmentEnd.toString(), request.getMode());

			HttpResponse alternativeRouteResponse = getRoute(alternativeRoute, true);
			responseBodyWithAlternatives = EntityUtils.toString(alternativeRouteResponse.getEntity(), "UTF-8");
			
			
			
			//Find shortest alternative without block
			JSONObject shortestRouteLegs = getLongestAlternativeForBlockedSegment(responseBodyWithAlternatives);

			//Get route from original start to start of block polyline
			UserRouteRequest RouteToBlockStart = new UserRouteRequest(request.getOrigin(), blockSegmentStart.toString(), request.getMode());
			responseToBlockSegmentStart = getRoute(RouteToBlockStart, false);
			responseBodyToBlockSegmentStart = EntityUtils.toString(responseToBlockSegmentStart.getEntity(), "UTF-8");
			
			
			//Get route from end of block polyline to original destination
			UserRouteRequest RouteFromBlockEnd = new UserRouteRequest(blockSegmentEnd.toString(), request.getDestination(), request.getMode());
			responseFromBlockSegmentEnd = getRoute(RouteFromBlockEnd, false);
			responseBodyFromBlockSegmentEnd = EntityUtils.toString(responseFromBlockSegmentEnd.getEntity(), "UTF-8");
			
			finalRouteWithoutBlock = combineRouteLegs(responseBodyToBlockSegmentStart, shortestRouteLegs.toString(), responseBodyFromBlockSegmentEnd);
			}
			
		catch(IOException|ParseException|

	NullPointerException e)
	{
		e.printStackTrace();
	}

	return new ResponseEntity<String>(finalRouteWithoutBlock, HttpStatus.valueOf(originalResponse.getStatusLine().getStatusCode()));
	}

	public LatLng getStartLatLngBlockSegment(String jsonResponse) {
		
		int stepsBefore = 0;
		try {
			JSONArray routes  = new JSONObject(jsonResponse).getJSONArray("routes");
			
			JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
			JSONArray steps = legs.getJSONObject(0).getJSONArray("steps");
				
			Double lat = steps.getJSONObject(steps.length()-1-stepsBefore).getJSONObject("start_location").getDouble("lat");
			Double lng = steps.getJSONObject(steps.length()-1-stepsBefore).getJSONObject("start_location").getDouble("lng");
				
			
			return new LatLng(lat, lng);
			}
		catch(Exception e) {
			logger.error("getSegment1EndLatLng Error: " + e.getMessage());
			return null;
		}
	}

	public LatLng getEndLatLngBlockSegment(String jsonResponse, double lat, double lng) {
		

		int stepsAfter = 0;
		
		JSONArray routes  = new JSONObject(jsonResponse).getJSONArray("routes");
		JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
		JSONArray steps = legs.getJSONObject(0).getJSONArray("steps");
		
		for(int i = 0; i < steps.length(); i++) {
			
			JSONObject currentStep = steps.getJSONObject(i).getJSONObject("start_location");
			if(currentStep.getDouble("lat") == lat && currentStep.getDouble("lng") == lng) {

				Double endLat = steps.getJSONObject(i+1+stepsAfter).getJSONObject("end_location").getDouble("lat");
				Double endLng = steps.getJSONObject(i+1+stepsAfter).getJSONObject("end_location").getDouble("lng");
					
				System.out.println(endLat+","+endLng);
				return new LatLng(endLat, endLng);
			}
		}
		return null;
	}
	
	public JSONObject getLongestAlternativeForBlockedSegment(String response) {
		

		JSONObject jsonResponse = new JSONObject(response);
		JSONArray routes  = jsonResponse.getJSONArray("routes");
		
		//get first route distance
		int longestDistance = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getInt("value");
		int longestRoutePos = 0;
		
		//iterate rest (if any exist)
		for (int i = 1; i < routes.length(); i++) {
			JSONObject leg= routes.getJSONObject(i).getJSONArray("legs").getJSONObject(0);
			int currentDistance = leg.getJSONObject("distance").getInt("value");
			
			//longest alternative should not be the original route
			if(currentDistance > longestDistance)
				longestRoutePos = i;
			longestDistance = currentDistance;
		}
	
		//Remove extra routes -> newRoute

		ArrayList<String> list = new ArrayList<String>();  
	    list.add(routes.get(longestRoutePos).toString());
		
		JSONArray newRoute = new JSONArray(list);
		
		//replace jsonResponse['routes'] with newRoute
		
		
		for(int i=0; i < routes.length(); i++) {
			if(i != longestRoutePos)
				routes.remove(i);
		}
		
		return jsonResponse;
	}
}

