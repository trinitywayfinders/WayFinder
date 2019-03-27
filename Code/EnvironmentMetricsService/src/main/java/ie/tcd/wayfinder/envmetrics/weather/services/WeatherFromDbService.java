package ie.tcd.wayfinder.envmetrics.weather.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ie.tcd.wayfinder.envmetrics.exceptions.ValueNotAcceptedException;
import ie.tcd.wayfinder.envmetrics.weather.model.WeatherResponse;

@Component
@Service
public class WeatherFromDbService {

	public WeatherResponse getWeatherFromDatabase(BigDecimal latitude, BigDecimal longitude)
			throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		response.setCondition("Clear");
		response = response.icon("11d");
		response.setLatitude(Float.parseFloat("12.23"));
		response.setLongitude(Float.parseFloat("12.23"));
		response.setOverall("Good");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}

}
