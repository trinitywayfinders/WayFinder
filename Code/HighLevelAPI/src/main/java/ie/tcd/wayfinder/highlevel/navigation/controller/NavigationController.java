package ie.tcd.wayfinder.highlevel.navigation.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.tcd.wayfinder.highlevel.navigation.request.UserRouteRequest;
import ie.tcd.wayfinder.highlevel.navigation.type.TravelMode;
import ie.tcd.wayfinders.restLibrary.Library;

@CrossOrigin(origins = {"*"}, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
@RestController
public class NavigationController {
	@Value("${spring.route.api.url}")
	private String RoutingUrl;
	
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
 
    @GetMapping("/navigation/start/{startLat}/{startLong}/destination/{destLat}/{destLong}/{mode}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> findRoute(@AuthenticationPrincipal Principal principal, @PathVariable Float startLong, @PathVariable Float startLat, @PathVariable Float destLong, @PathVariable Float destLat, @PathVariable TravelMode mode) throws IOException
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
        
        String username = principal.getName();
                
        UserRouteRequest request = new UserRouteRequest(startLat + "," + startLong, destLat + "," + destLong, mode, username);
        
        String jsonRequestContent = objectMapper().writeValueAsString(request);
                
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
       
        String url = RoutingUrl + "/api/route/";
        System.out.println("Call made to " + url);
        HttpResponse response = Library.POST(url, Optional.of(headers), Optional.of(jsonRequestContent));
                
         HttpEntity responseEntity = response.getEntity();
         String responseString = EntityUtils.toString(responseEntity);
                       
        return new ResponseEntity<String>(responseString, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/navigation/start/{start}/destination/{dest}/{mode}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> findRoute(@AuthenticationPrincipal Principal principal, @PathVariable String start, @PathVariable String dest, @PathVariable TravelMode mode) throws IOException
    {
    	String username = principal.getName();
    	
        UserRouteRequest request = new UserRouteRequest(start, dest, mode, username);
        
        String jsonRequestContent = objectMapper().writeValueAsString(request);
                
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
       
        String url = RoutingUrl + "/api/route/";
        System.out.println("Call made to " + url);
        HttpResponse response = Library.POST(url, Optional.of(headers), Optional.of(jsonRequestContent));
                
         HttpEntity responseEntity = response.getEntity();
         String responseString = EntityUtils.toString(responseEntity);
         
         System.out.println("done...");
                       
        return new ResponseEntity<String>(responseString, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
    }
    
    
    
//  //Avoid LatLng endpoint
//    @GetMapping("/navigation/start/{startLat}/{startLong}/destination/{destLat}/{destLong}/{mode}/avoid/{avoidLat}/{avoidLng}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public ResponseEntity<String> findRouteWithBlock(@AuthenticationPrincipal Principal principal, @PathVariable Float startLong, @PathVariable Float startLat, @PathVariable Float destLong, @PathVariable Float destLat, 
//    		@PathVariable TravelMode mode, @PathVariable Float avoidLat, @PathVariable Float avoidLong) throws IOException
//    {
//    	String username = principal.getName();
//        
//        if (startLong == null || startLat == null) return new ResponseEntity<String>("Starting location cannot be empty!",
//                                                               HttpStatus.BAD_REQUEST);
//
//        if (destLong == null || destLat== null) return new ResponseEntity<String>("Destination cannot be empty!",
//                                                                     HttpStatus.BAD_REQUEST);
//        
//        if (startLat > 90 || startLat < -90) return new ResponseEntity<String>("Latitude of Starting point is invalid!",
//                HttpStatus.BAD_REQUEST);
//
//        if (destLong >180 || destLong < -180 ) return new ResponseEntity<String>("Longitude of Destination point is invalid!",
//                      HttpStatus.BAD_REQUEST);
//        
//        if (destLat > 90 || destLat < -90) return new ResponseEntity<String>("Latitude of Destination point is invalid!",
//                HttpStatus.BAD_REQUEST);
//
//        if (startLong >180 || startLong < -180 ) return new ResponseEntity<String>("Longitude of Starting point is invalid!",
//                      HttpStatus.BAD_REQUEST);
//        
//        if (avoidLong >180 || avoidLong < -180 ) return new ResponseEntity<String>("Longitude of Block point is invalid!",
//                      HttpStatus.BAD_REQUEST);
//        
//        if (avoidLat > 90 || avoidLat < -90) return new ResponseEntity<String>("Latitude of Block point is invalid!",
//                HttpStatus.BAD_REQUEST);
//        
//        UserRouteRequest request = new UserRouteRequest(startLat + "," + startLong, destLat + "," + destLong, mode, username);
//        
//        String jsonRequestContent = objectMapper().writeValueAsString(request);
//                
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/json");
//       
//        String endpoint = "http://localhost:8082/api/route/avoid/"+avoidLat+"/"+avoidLong+"/";
//        
//        HttpResponse response = Library.POST(endpoint, Optional.of(headers), Optional.of(jsonRequestContent));
//                
//         HttpEntity responseEntity = response.getEntity();
//         String responseString = EntityUtils.toString(responseEntity);
//                       
//        return new ResponseEntity<String>(responseString, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
//    }
//    
//  //Avoid LatLng endpoint
//    @GetMapping("/navigation/start/{start}/destination/{dest}/{mode}/avoid/{avoidLat}/{avoidLng}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public ResponseEntity<String> findRouteWithBlock(@AuthenticationPrincipal Principal principal, @PathVariable String start, @PathVariable String dest, @PathVariable TravelMode mode, @PathVariable Float avoidLat, @PathVariable Float avoidLng) throws IOException
//    {
//    	String username = principal.getName();
//      
//        if (avoidLng >180 || avoidLng < -180 ) return new ResponseEntity<String>("Longitude of Block point is invalid!",
//                      HttpStatus.BAD_REQUEST);
//        
//        if (avoidLat > 90 || avoidLat < -90) return new ResponseEntity<String>("Latitude of Block point is invalid!",
//                HttpStatus.BAD_REQUEST);
//        
//        UserRouteRequest request = new UserRouteRequest(start, dest, mode, username);
//        
//        String jsonRequestContent = objectMapper().writeValueAsString(request);
//                
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/json");
//       
//        String endpoint = "http://localhost:8082/api/route/avoid/"+avoidLat+"/"+avoidLng+"/";
//        
//        HttpResponse response = Library.POST(endpoint, Optional.of(headers), Optional.of(jsonRequestContent));
//                
//         HttpEntity responseEntity = response.getEntity();
//         String responseString = EntityUtils.toString(responseEntity);
//                       
//        return new ResponseEntity<String>(responseString, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
//    }
    
  //Avoid LatLng endpoint
    @GetMapping("/navigation/start/{startLat}/{startLong}/destination/{destLat}/{destLong}/{mode}/avoid/{avoidLat}/{avoidLng}/{username}")
    public ResponseEntity<String> findRouteWithBlock(@PathVariable Float startLong, @PathVariable Float startLat, @PathVariable Float destLong, @PathVariable Float destLat, 
    		@PathVariable TravelMode mode, @PathVariable Float avoidLat, @PathVariable Float avoidLong, @PathVariable String username) throws IOException
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
        
        if (avoidLong >180 || avoidLong < -180 ) return new ResponseEntity<String>("Longitude of Block point is invalid!",
                      HttpStatus.BAD_REQUEST);
        
        if (avoidLat > 90 || avoidLat < -90) return new ResponseEntity<String>("Latitude of Block point is invalid!",
                HttpStatus.BAD_REQUEST);
        
        UserRouteRequest request = new UserRouteRequest(startLat + "," + startLong, destLat + "," + destLong, mode, username);
        
        String jsonRequestContent = objectMapper().writeValueAsString(request);
                
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        
        String endpoint = RoutingUrl + "/api/route/avoid/"+avoidLat+"/"+avoidLong+"/";
        
        HttpResponse response = Library.POST(endpoint, Optional.of(headers), Optional.of(jsonRequestContent));
                
         HttpEntity responseEntity = response.getEntity();
         String responseString = EntityUtils.toString(responseEntity);
                       
        return new ResponseEntity<String>(responseString, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
    }
    
  //Avoid LatLng endpoint
    @GetMapping("/navigation/start/{start}/destination/{dest}/{mode}/avoid/{avoidLat}/{avoidLng}/{username}")
    public ResponseEntity<String> findRouteWithBlock(@PathVariable String start, @PathVariable String dest, @PathVariable TravelMode mode, @PathVariable Float avoidLat, @PathVariable Float avoidLng, @PathVariable String username) throws IOException
    {
      
        if (avoidLng >180 || avoidLng < -180 ) return new ResponseEntity<String>("Longitude of Block point is invalid!",
                      HttpStatus.BAD_REQUEST);
        
        if (avoidLat > 90 || avoidLat < -90) return new ResponseEntity<String>("Latitude of Block point is invalid!",
                HttpStatus.BAD_REQUEST);
        
        UserRouteRequest request = new UserRouteRequest(start, dest, mode, username);
        
        String jsonRequestContent = objectMapper().writeValueAsString(request);
                
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        String endpoint = RoutingUrl + "/api/route/avoid/"+avoidLat+"/"+avoidLng+"/";
        
        HttpResponse response = Library.POST(endpoint, Optional.of(headers), Optional.of(jsonRequestContent));
                
         HttpEntity responseEntity = response.getEntity();
         String responseString = EntityUtils.toString(responseEntity);
                       
        return new ResponseEntity<String>(responseString, HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
}
    
    
    @Bean
    public ObjectMapper objectMapper() {
    	return new ObjectMapper();
    }

}
