package ie.tcd.wayfinder.highlevel.navigation;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.tcd.wayfinders.restLibrary.Library;


@RestController
@EnableAutoConfiguration
public class NavigationController {

    @RequestMapping("/")
    public String index()
    {
        return "Wayfinders Springboot PoC!";
    }

    /*
     * @PostMapping("/getRoute") public ResponseEntity<String>
     * FindRoute(@RequestBody RouteInput requestBody) {
     * 
     * if(requestBody.Start == "Dublin" && requestBody.Destination == "China") {
     * 
     * return new ResponseEntity<String>("!!", HttpStatus.OK); } return new
     * ResponseEntity<String>("No ROUTES", HttpStatus.BAD_REQUEST); }
     */

    @GetMapping("/navigation/start/{startLong}/{startLat}/destination/{destLong}/{destLat}/")
    public ResponseEntity<String> findRoute(@PathVariable Float startLong, @PathVariable Float startLat, @PathVariable Float destLong, @PathVariable Float destLat) throws IOException
    {
        
        if (startLong == null || startLat == null) return new ResponseEntity<String>("Starting location cannot be empty!",
                                                               HttpStatus.BAD_REQUEST);

        if (destLong == null || destLat== null) return new ResponseEntity<String>("Destination cannot be empty!",
                                                                     HttpStatus.BAD_REQUEST);
        
        if (startLat > 90 || startLat < -90) return new ResponseEntity<String>("Latitude of Starting point is invalid!",
                HttpStatus.BAD_REQUEST);

        if (destLong >180 || destLong < -180 ) return new ResponseEntity<String>("Longitude of Destination point is invalid!",
                      HttpStatus.BAD_REQUEST);
        
        if (destLat > 90 || destLat < -90) return new ResponseEntity<String>("Latitude of Destination point is invalid!",
                HttpStatus.BAD_REQUEST);

        if (startLong >180 || startLong < -180 ) return new ResponseEntity<String>("Longitude of Starting point is invalid!",
                      HttpStatus.BAD_REQUEST);
       
        HttpResponse response = Library.GET("http://localhost:3000/path", Optional.empty());
        
        HttpEntity responseEntity = response.getEntity();
        String responseXml = EntityUtils.toString(responseEntity);
     
        return new ResponseEntity<String>(responseXml, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
    }

}