package ie.tcd.wayfinders.restLibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.StatusLine;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import ie.tcd.wayfinders.restLibrary.Library;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LibraryTest {

    @Test
    public void get200OkNoProperties() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        HttpResponse response = Library.GET(url, Optional.empty());
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void get200OkWithProperties() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("header1", "val1");
        headers.put("header2", "val2");
        headers.put("header3", "val3");
                
        
        HttpResponse response = Library.GET(url, Optional.of(headers));
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void putWithPropertiesNoBody() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("header1", "val1");
        headers.put("header2", "val2");
        headers.put("header3", "val3");
                
        
        HttpResponse response = Library.PUT(url, Optional.of(headers), Optional.empty());
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void putNoPropertiesNoBody() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);
        
        HttpResponse response = Library.PUT(url, Optional.empty(), Optional.empty());
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void putWithBody() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);
        
        String json = "DATA:{\r\n" + 
                "  \"names\": \"typicodes\"\r\n" + 
                "}";
        
        HttpResponse response = Library.PUT(url, Optional.empty(), Optional.of(json));
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void postWithPropertiesWithBody() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);
        
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("header1", "val1");
        headers.put("header2", "val2");
        headers.put("header3", "val3");
                
        String json = "DATA:{\r\n" + 
                "  \"names\": \"typicodes\"\r\n" + 
                "}";
        
        HttpResponse response = Library.POST(url, Optional.empty(), Optional.of(json));
        assertEquals(201, response.getStatusLine().getStatusCode());
    }

    @Test
    public void postWithBody() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);
        
        String json = "DATA:{\r\n" + 
                "  \"names\": \"typicodes\"\r\n" + 
                "}";
        
        HttpResponse response = Library.POST(url, Optional.empty(), Optional.of(json));
        assertEquals(201, response.getStatusLine().getStatusCode());
    }

    @Test
    public void deleteWithProperties() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("header1", "val1");
        headers.put("header2", "val2");
        headers.put("header3", "val3");
                
        
        HttpResponse response = Library.DELETE(url, Optional.of(headers));
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    public void deleteNoProperties() throws IOException
    {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);
        
        HttpResponse response = Library.DELETE(url, Optional.empty());
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

}
