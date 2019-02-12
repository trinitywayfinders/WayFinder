import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

public class Library {

    public static String GET(String URL, List<List<String>> properties) throws IOException {
        
        return handleRest("GET", URL, properties);
    }
    
    public static String PUT(String URL, List<List<String>> properties) throws IOException {
        
        return handleRest("PUT", URL, properties);
    }
    
    public static String POST(String URL, List<List<String>> properties) throws IOException {
        
        return handleRest("POST", URL, properties);
    }
    
    public static String DELETE(String URL, List<List<String>> properties) throws IOException {
        
        return handleRest("DELETE", URL, properties);
    }
    
    
    private static String handleRest(String requestMethod, String URL, List<List<String>>  properties) throws IOException {
            
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod(requestMethod);
        
        if ( properties != null && !properties.isEmpty()) {
            for(List<String> property : properties) {
                if(property.size() == 2) {
                    System.out.println(property.get(0)+"\n"+property.get(1));
                    connection.setRequestProperty(property.get(0), property.get(1));
            }
                else
                    //malformed
                    throw new IOException("Malformed header...");
            }
        }
        
        
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code: " + responseCode);
          
        return HandleResponse(connection, responseCode);
    }
    
    private static String HandleResponse(HttpURLConnection connection, int responseCode) throws IOException {
        
        BufferedReader br;
        StringBuilder sb;
        String output;
        
        switch(responseCode) {
            case 200:
            case 202:
            case 204:
                br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                sb = new StringBuilder();
                output = "";
                
                while ((output = br.readLine()) != null) {
                  sb.append(output);
                }
                br.close();
                return sb.toString();
                
            case 400:
            case 404:
            case 500: 
                br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
                sb = new StringBuilder();
                output = "";
                
                while ((output = br.readLine()) != null) {
                  sb.append(output);
                }
                br.close();
                return sb.toString();
                
        }
        
        
        return null;
    }
}
