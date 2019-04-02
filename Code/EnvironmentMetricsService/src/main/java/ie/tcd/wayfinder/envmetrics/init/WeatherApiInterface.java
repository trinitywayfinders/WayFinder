package ie.tcd.wayfinder.envmetrics.init;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.tcd.wayfinder.envmetrics.exceptions.ValueNotAcceptedException;
import ie.tcd.wayfinder.envmetrics.model.Error;
import ie.tcd.wayfinder.envmetrics.weather.model.WeatherResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "WeatherApi", description = "the Weather API")
public interface WeatherApiInterface {

	@ApiOperation(value = "Get Weather information for Latitude and longitude", nickname = "getWeather", notes = "", response = WeatherResponse.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Weather Object", response = WeatherResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 404, message = "Weather Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/api/environment/weather/{Lat}/{Long}", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<WeatherResponse> getRoute(
			@ApiParam(value = "Current latitude", required = true) @PathVariable("Lat") String lat,
			@ApiParam(value = "Current Longitude", required = true) @PathVariable("Long") String _long)
			throws ValueNotAcceptedException;

}
