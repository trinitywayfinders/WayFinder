package ie.tcd.wayfinder.envmetrics.init;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.tcd.wayfinder.envmetrics.exceptions.ValueNotAcceptedException;
import ie.tcd.wayfinder.envmetrics.weather.model.WeatherResponse;
import io.swagger.annotations.ApiParam;

@Controller
public class WeatherApiResource implements WeatherApiInterface {

	private static final Logger log = LoggerFactory.getLogger(WeatherApiResource.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public WeatherApiResource(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<WeatherResponse> getRoute (
			@ApiParam(value = "Current latitude", required = true) @PathVariable("Lat") String lat,
			@ApiParam(value = "Current Longitude", required = true) @PathVariable("Long") String _long) throws ValueNotAcceptedException
	{
		String accept = request.getHeader("Accept");
		WeatherResponse response = new WeatherResponse();
		response.setCondition("Clear");
		response = response.icon("11d");
		response.setLatitude(new BigDecimal("12.23"));
		response.setLongitude(new BigDecimal("12.23"));
		response.setOverall("Good");
		return new ResponseEntity<WeatherResponse>(response, HttpStatus.OK);
	}
}
