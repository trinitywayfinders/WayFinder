package ie.tcd.wayfinders.restLibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class Test {
    public static void main(String [] args) throws IOException {
        
        String url = "https://my-json-server.typicode.com/typicode/demo/profile";
 
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Accept", "application/json");
                
        String json = "DATA:{\r\n" + 
                "  \"names\": \"typicodes\"\r\n" + 
                "}";
        
        HttpResponse response = (Library.DELETE(url, Optional.of(headers)));
        HttpEntity responseEntity = response.getEntity();
        String reponseXml = EntityUtils.toString(responseEntity);
        System.out.println("\n\n"+response.getStatusLine());
        System.out.println("\n\n\nDATA:"+reponseXml);
        
    }
}
