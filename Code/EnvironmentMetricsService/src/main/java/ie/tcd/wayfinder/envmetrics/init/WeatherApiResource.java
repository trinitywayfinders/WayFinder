package ie.tcd.wayfinder.envmetrics.init;

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
import ie.tcd.wayfinder.envmetrics.weather.services.WeatherFromApiService;
import io.swagger.annotations.ApiParam;

@Controller
public class WeatherApiResource implements WeatherApiInterface {

	private static final Logger logger = LoggerFactory.getLogger(WeatherApiResource.class);

	@SuppressWarnings("unused")
	private final ObjectMapper objectMapper;

	@SuppressWarnings("unused")
	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public WeatherApiResource(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}  

	public ResponseEntity<WeatherResponse> getRoute(
			@ApiParam(value = "Current latitude", required = true) @PathVariable("Lat") String latitude,
			@ApiParam(value = "Current Longitude", required = true) @PathVariable("Long") String longitude)
			throws ValueNotAcceptedException {
		logger.info(String.format("Latitude = %s, longitude = %s", latitude, longitude));
		WeatherResponse response = new WeatherFromApiService().getWeatherFromApi(latitude, longitude);
		return new ResponseEntity<WeatherResponse>(response, HttpStatus.OK);
	}
}
