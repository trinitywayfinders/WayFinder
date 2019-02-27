package ie.tcd.wayfinder.etcd;

import ie.tcd.wayfinders.restLibrary.Library;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpResponse;

public class ETCDLibrary {

	private String baseUrl = "http://35.246.14.12:2379/v2/keys/";
		
	public HttpResponse ReadDirectory(String directoryName) throws IOException {
		
		String url = (baseUrl + directoryName).trim();
		HttpResponse response = Library.GET(url, Optional.empty());
		return response;
		
	}
	
	public HttpResponse DeleteDirectory(String directoryName) throws IOException {
		
		String url = baseUrl + directoryName+"?dir=true";
		HttpResponse response = Library.DELETE(url, Optional.empty());
		return response;
	
	}
	
	public HttpResponse CreateDirectory(String directoryName) throws IOException {
		
		String url = baseUrl + directoryName + "?dir=true";
		HttpResponse response = Library.PUT(url, Optional.empty(), Optional.empty());
		return response;
	}

	public HttpResponse CreateKey(String directoryName, String key, String value) throws IOException {
		
		String url = baseUrl + directoryName + "/"+key+"?value="+value;
		HttpResponse response = Library.PUT(url, Optional.empty(), Optional.empty());
		return response;
	}

	public HttpResponse UpdateKey(String directoryName, String key, String value) throws IOException {

		return CreateKey(directoryName, key, value);
	}

	public HttpResponse ReadKey(String directoryName, String key) throws IOException {
		
		String url = baseUrl + directoryName;
		HttpResponse response = Library.GET(url, Optional.empty()); 
	    return response;
	}

	public HttpResponse DeleteKey(String directoryName, String key) throws IOException {

		String url = baseUrl + directoryName + "/"+key;
		HttpResponse response = Library.DELETE(url, Optional.empty());
		return response;
	}
}
