package ie.tcd.wayfinders.restLibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import net.minidev.json.JSONObject;

public class Test {
    public static void main(String [] args) throws IOException {
        
        String url = "https://my-json-server.typicode.com/typicode/demo/posts";
 
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("header1", "val1");
        headers.put("header2", "val2");
        headers.put("header3", "val3");
                
        HttpEntity response = (Library.GET(url, Optional.of(headers))).getEntity();
        //String x = response.toString();
        //System.out.println("!!!\n"+x);
        
        //JSONObject result = new JSONObject(response);
        //HttpEntity data = response.getEntity();
        
        String reponseXml = EntityUtils.toString(response);
        
        System.out.println("\n\n\nDATA:"+reponseXml);
        
    }
}
