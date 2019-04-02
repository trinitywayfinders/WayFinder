package ie.tcd.wayfind.lowlevel.request;


import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import ie.tcd.wayfinders.restLibrary.Library;

public class EnvironmentMetrics {
	
	public float Latitude;
	public float Longitude;
	public String url = "http://127.0.0.1:22113/api/environment/weather/";
	
	public EnvironmentMetrics(float latitude, float longitude) {
		super();
		Latitude = latitude;
		Longitude = longitude;
	}
	
	public String getWeatherCondition() {
		
		String url = this.url+this.Latitude+"/"+this.Longitude+"/";
		
		try {
			HttpResponse response = Library.GET(url, Optional.empty());
			
			HttpEntity responseEntity = response.getEntity();
			String responseString = EntityUtils.toString(responseEntity);
		
			JSONObject responseJson = new JSONObject(responseString);
			
			return responseJson.getString("overall");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
