package ie.tcd.wayfinder.highlevel.navigation.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ie.tcd.wayfinder.highlevel.navigation.type.TravelMode;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class UserRouteRequest {
	@JsonProperty("origin")
	private String origin;
	
	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("mode")
	private TravelMode mode;
	
	@JsonCreator
	public UserRouteRequest(@JsonProperty("origin") String origin, @JsonProperty("destination") String destination, @JsonProperty("mode") TravelMode mode) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.mode = mode;
	}

	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public TravelMode getMode() {
		return mode;
	}
	
	public void setMode(TravelMode mode) {
		this.mode = mode;
	}
	
	public MultiValueMap<java.lang.String,java.lang.String> toParams() {
		MultiValueMap<String,String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("origin", this.origin);
		params.add("destination", this.destination);
		params.add("mode", this.mode.toString());
		
		return params;
	}
}
