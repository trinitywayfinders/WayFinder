package ie.tcd.wayfinders.restLibrary;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class Library {

    public static HttpResponse GET(String URL, Optional<Map<String, String>> headers) throws IOException
    {
        HttpGet request = new HttpGet(URL);
        return handleRest(request, headers);
    }

    public static HttpResponse PUT(String URL, Optional<Map<String, String>> headers, Optional<String> body) throws IOException
    {
        HttpPut request = new HttpPut(URL);

        if (body.isPresent()) request.setEntity(new StringEntity(body.get()));
        return handleRest(request, headers);
    }

    public static HttpResponse POST(String URL, Optional<Map<String, String>> headers, Optional<String> body) throws IOException
    {
        HttpPost request = new HttpPost(URL);

        if (body.isPresent()) request.setEntity(new StringEntity(body.get()));
        return handleRest(request, headers);
    }

    public static HttpResponse DELETE(String URL, Optional<Map<String, String>> headers) throws IOException
    {
        HttpDelete request = new HttpDelete(URL);
        return handleRest(request, headers);
    }

    private static HttpResponse handleRest(HttpRequestBase requestMethod, Optional<Map<String, String>> headers) throws IOException
    {

        if (headers.isPresent())
        {
            for (Entry<String, String> header : headers.get().entrySet())
            {
                System.out.println(header.getKey() + "\n" + header.getValue());
                requestMethod.setHeader(header.getKey(), header.getValue());
            }
        }

        HttpClient client = HttpClientBuilder.create().build();
        
        HttpResponse response = client.execute(requestMethod);
        
        return response;
    }
}