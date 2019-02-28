package ie.tcd.wayfinder.etcd;

import ie.tcd.wayfinders.restLibrary.Library;
import java.io.IOException;
import java.util.Optional;
import org.apache.http.HttpResponse;

public class ETCDLibrary {

	private String baseUrl = "http://35.246.14.12:2379/v2/keys/";
		
	public HttpResponse ReadDirectory(String directoryName) throws IOException {
		
		String url = (baseUrl + directoryName).trim();
		return Library.GET(url, Optional.empty());
		
	}
	
	public HttpResponse DeleteDirectory(String directoryName) throws IOException {
		
		String url = baseUrl + directoryName+"?dir=true";
		return Library.DELETE(url, Optional.empty());
	}
	
	public HttpResponse CreateDirectory(String directoryName) throws IOException {
		
		String url = baseUrl + directoryName + "?dir=true";
		return Library.PUT(url, Optional.empty(), Optional.empty());
	}

	public HttpResponse CreateKey(String directoryName, String key, String value) throws IOException {
		
		String url = baseUrl + directoryName + "/"+key+"?value="+value;
		return Library.PUT(url, Optional.empty(), Optional.empty());
	}

	public HttpResponse UpdateKey(String directoryName, String key, String value) throws IOException {

		return CreateKey(directoryName, key, value);
	}

	public HttpResponse ReadKey(String directoryName, String key) throws IOException {
		
		String url = baseUrl + directoryName;
		return Library.GET(url, Optional.empty()); 
	}

	public HttpResponse DeleteKey(String directoryName, String key) throws IOException {

		String url = baseUrl + directoryName + "/"+key;
		return Library.DELETE(url, Optional.empty());
	}
}
