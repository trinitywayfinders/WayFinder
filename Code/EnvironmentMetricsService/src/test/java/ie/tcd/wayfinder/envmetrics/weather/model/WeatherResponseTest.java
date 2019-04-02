package ie.tcd.wayfinder.envmetrics.weather.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ie.tcd.wayfinder.envmetrics.exceptions.ValueNotAcceptedException;

public class WeatherResponseTest {

	@Test
	public void checkOverallGood() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Good";
		response.overall(checkString);
		assertEquals(response.getOverall(), checkString);
	}

	@Test
	public void checkOverallBad() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Bad";
		response.overall(checkString);
		assertEquals(response.getOverall(), checkString);
	}

	@Test
	public void checkOverallUnknown() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Unknown";
		response.overall(checkString);
		assertEquals(response.getOverall(), checkString);
	}

	@Test(expected = ValueNotAcceptedException.class)
	public void checkOverallNegativeCase() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		response.overall("Negative");
	}

	@Test
	public void checkConditionThunderstorm() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Thunderstorm";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test
	public void checkConditionDrizzle() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Drizzle";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test
	public void checkConditionRain() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Rain";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test
	public void checkConditionSnow() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Snow";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test
	public void checkConditionAtmosphere() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Atmosphere";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test
	public void checkConditionClear() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Clear";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test
	public void checkConditionUnknown() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Unknown";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test
	public void checkConditionClouds() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "Clouds";
		response.condition(checkString);
		assertEquals(response.getCondition(), checkString);
	}

	@Test(expected = ValueNotAcceptedException.class)
	public void checkConditionNegativeCase() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		response.condition("Negative");
	}

	@Test
	public void checkIcon11d() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "11d";
		response.icon(checkString);
		assertEquals(response.getIcon(), checkString);
	}

	@Test
	public void checkIcon09d() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "09d";
		response.icon(checkString);
		assertEquals(response.getIcon(), checkString);
	}

	@Test
	public void checkIcon03n() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		String checkString = "03n";
		response.icon(checkString);
		assertEquals(response.getIcon(), checkString);
	}

	@Test(expected = ValueNotAcceptedException.class)
	public void checkIconNegativeCase1() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		response.icon("Negative");
	}

	@Test(expected = ValueNotAcceptedException.class)
	public void checkIconNegativeCase2() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		response.icon("12345d");
	}

	@Test(expected = ValueNotAcceptedException.class)
	public void checkIconNegativeCase3() throws ValueNotAcceptedException {
		WeatherResponse response = new WeatherResponse();
		response.icon("125d");
	}

}
