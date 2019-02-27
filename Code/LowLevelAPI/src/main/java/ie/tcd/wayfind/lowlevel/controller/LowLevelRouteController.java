package ie.tcd.wayfind.lowlevel.controller;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import ie.tcd.wayfind.lowlevel.request.UserRouteRequest;
import ie.tcd.wayfinders.restLibrary.Library;

@RestController
public class LowLevelRouteController {
    private static final Logger logger = LoggerFactory.getLogger(LowLevelRouteController.class);
    private static final String endpoint = "https://maps.googleapis.com/maps/api/directions/json";
    private static String apiKey = "AIzaSyB2NHLaqVDF0uSmuNBMXI3DVsUanzdRD7Q";
    
	@PostMapping("/api/route")
	public String retriveRoute(@RequestBody UserRouteRequest request) {
		
		logger.debug("Origin: " + request.getOrigin());
		logger.debug("Destinnation: " + request.getDestination());
		logger.debug("Mode: " + request.getMode().toString());
		
		// Parameter check goes here
		MultiValueMap<java.lang.String,java.lang.String> params = request.toParams();
		params.add("key", apiKey);
		
		UriComponents uri;
		HttpResponse response = null;
		
		try {
			UriComponentsBuilder.newInstance();
			uri = UriComponentsBuilder.fromUriString(endpoint).queryParams(params).build();
			response = Library.GET(uri.toString(), Optional.empty());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String responseBody = null;
		
		try {
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseBody;
	}
}
