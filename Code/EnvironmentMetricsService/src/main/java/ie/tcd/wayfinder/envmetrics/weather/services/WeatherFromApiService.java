package ie.tcd.wayfinder.envmetrics.weather.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ie.tcd.wayfinder.envmetrics.weather.model.WeatherResponse;
import ie.tcd.wayfinders.restLibrary.Library;

public class WeatherFromApiService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherFromApiService.class);

	private static final String ApiKey = "461f3ea20608827335645fcfc8bf0d71";
	private static final String URLBasePath = "http://api.openweathermap.org/data/2.5/weather";
	private static String params = "lat=%s&lon=%s&APPID=%s";

	@SuppressWarnings("unchecked")
	public WeatherResponse getWeatherFromApi(String latitude, String longitude) {
		WeatherResponse returnObject = new WeatherResponse();
		returnObject.setLatitude(Float.parseFloat(latitude));
		returnObject.setLongitude(Float.parseFloat(longitude));
		try {
			HttpResponse response = Library.POST(URLBasePath + '?' + String.format(params, latitude, longitude, ApiKey),
					Optional.of(new HashMap<String, String>()), Optional.ofNullable(null));
			String responseFromServer = EntityUtils.toString(response.getEntity());
			System.out.println(responseFromServer);
			JSONParser parser = new JSONParser();
			try {
				JSONObject weatherObject = (JSONObject) parser.parse(responseFromServer);
				JSONArray weatherList = (JSONArray) weatherObject.getOrDefault("weather", new JSONArray());
				System.out.println(weatherList);
				if (weatherList.size() > 0) {
					JSONObject currentWeather = (JSONObject) weatherList.get(0);
					String mainCondition = String.valueOf(currentWeather.getOrDefault("main", WeatherResponse.UNKNOWN));
					if (mainCondition.equals(WeatherResponse.CLEAR) || mainCondition.equals(WeatherResponse.CLOUDS)) {
						returnObject.setOverall(WeatherResponse.GOOD);
					} else {
						returnObject.setOverall(WeatherResponse.BAD);
					}
					returnObject.setCondition(mainCondition);
					returnObject
							.setIcon(String.valueOf(currentWeather.getOrDefault("icon", WeatherResponse.DEFAULT_ICON)));
					logger.info("Retrieved Weather Information");
				} else {
					returnObject.setOverall(WeatherResponse.UNKNOWN);
					returnObject.setCondition(WeatherResponse.UNKNOWN);
					returnObject.setIcon(WeatherResponse.DEFAULT_ICON);
					logger.warn("Unable to get the weather, hence switching to default");
				}
			} catch (ParseException e) {
				logger.error("Parse Exception", e);
			}
		} catch (IOException e) {
			logger.error("IO Exception", e);
		} catch (Exception e) {
			logger.error("Unknown Exception", e);
		}
		returnObject.setTimestamp(System.currentTimeMillis());
		logger.info("Returning the Weather Response");
		return returnObject;
	}
}
