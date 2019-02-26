package ie.tcd.wayfinder.etcd;

import ie.tcd.wayfinders.restLibrary.Library;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class ETCDLibrary {

	private static String baseUrl = "http://35.246.14.12:2379/v2/keys/";
		
	public static String ReadDirectory(String directoryName) throws IOException {
		
		String url = (baseUrl + directoryName).trim();
		HttpResponse response = Library.GET(url, Optional.empty());
		
		 HttpEntity responseEntity = response.getEntity();
	     String responseXml = EntityUtils.toString(responseEntity);
	     
	     return responseXml;
		
	}
	
	public static String DeleteDirectory(String directoryName) throws IOException {
		
		String url = baseUrl + directoryName+"?dir=true";
		
		HttpResponse response = Library.DELETE(url, Optional.empty());
		
		HttpEntity responseEntity = response.getEntity();
	    String responseXml = EntityUtils.toString(responseEntity);
	     
	    return responseXml;
	
	}
	
	public static String CreateDirectory(String directoryName) throws IOException {
		
		String url = baseUrl + directoryName + "?dir=true";
		HttpResponse response = Library.PUT(url, Optional.empty(), Optional.empty());
		
		 HttpEntity responseEntity = response.getEntity();
	     String responseXml = EntityUtils.toString(responseEntity);
	     
	     return responseXml;
	}

	public static String CreateKey(String directoryName, String key, String value) throws IOException {
		
		String url = baseUrl + directoryName + "/"+key+"?value="+value;
		HttpResponse response = Library.PUT(url, Optional.empty(), Optional.empty());
		
		HttpEntity responseEntity = response.getEntity();
	    String responseXml = EntityUtils.toString(responseEntity);
	     
	    return responseXml;
	}

	public static String UpdateKey(String directoryName, String key, String value) throws IOException {

		return CreateKey(directoryName, key, value);
	}


	public static String ReadKey(String directoryName, String key) throws IOException {
		String url = baseUrl + directoryName;

		HttpResponse response = Library.GET(url, Optional.empty());
		
		HttpEntity responseEntity = response.getEntity();
	    String responseXml = EntityUtils.toString(responseEntity);
	     
	    return responseXml;
	}

	public static String DeleteKey(String directoryName, String key) throws IOException {

		String url = baseUrl + directoryName + "/"+key;

		HttpResponse response = Library.DELETE(url, Optional.empty());
		
		HttpEntity responseEntity = response.getEntity();
	    String responseXml = EntityUtils.toString(responseEntity);
	     
	    return responseXml;
	}
}
