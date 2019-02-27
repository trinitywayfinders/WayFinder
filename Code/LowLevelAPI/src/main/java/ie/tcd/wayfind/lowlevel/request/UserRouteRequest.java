package ie.tcd.wayfind.lowlevel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ie.tcd.wayfind.lowlevel.type.TravelMode;

public class UserRouteRequest {
	@JsonProperty("origin")
	private String origin;
	
	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("mode")
	private TravelMode mode;
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestinatioin(String destination) {
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
