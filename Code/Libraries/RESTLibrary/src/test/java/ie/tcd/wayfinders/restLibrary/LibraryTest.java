package ie.tcd.wayfinders.restLibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.StatusLine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import ie.tcd.wayfinders.restLibrary.Library;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {

    @Test
    public void get_200OK_noProperties() throws IOException
    {
        String url = "http://test.com";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        HttpResponse response = Library.GET(url, Optional.empty());
        assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void get_200OK_withProperties() throws IOException
    {
        String url = "http://test.com";

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
        assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void get_404Exception() throws IOException
    {
        String url = "http://test.com";

        HttpClient httpClient = PowerMockito.mock(HttpClient.class);
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        StatusLine statusLine = PowerMockito.mock(StatusLine.class);
        
        PowerMockito.when(statusLine.getStatusCode()).thenReturn(404);
        PowerMockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);
        PowerMockito.when(httpClient.execute(httpGet)).thenThrow(HttpHostConnectException.class);
        
        HttpResponse response = Library.GET(url, Optional.empty());
        assertEquals(response.getStatusLine().getStatusCode(), 404);
    }

    public void put_withProperties_noBody() throws IOException
    {

    }

    public void put_noProperties_noBody() throws IOException
    {

    }

    public void put_withBody() throws IOException
    {

    }

    public void post_withProperties_noBody() throws IOException
    {

    }

    public void post_noProperties_noBody() throws IOException
    {

    }

    public void post_withBody() throws IOException
    {

    }

    public void delete_withProperties_noBody() throws IOException
    {

    }

    public void delete_noProperties_noBody() throws IOException
    {

    }

    public void delete_withBody() throws IOException
    {

    }

}
