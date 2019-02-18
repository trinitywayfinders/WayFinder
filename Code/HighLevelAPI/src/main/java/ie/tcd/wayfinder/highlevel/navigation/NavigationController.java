package ie.tcd.wayfinder.highlevel.navigation;

import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getRoute/start/{start}/destination/{destination}")
    public ResponseEntity<String> FindRoute(@PathVariable String start, @PathVariable String destination)
    {

        if (start.isEmpty()) return new ResponseEntity<String>("Starting location cannot be empty!",
                                                               HttpStatus.BAD_REQUEST);

        if (destination.isEmpty()) return new ResponseEntity<String>("Destinationcannot be empty!",
                                                                     HttpStatus.BAD_REQUEST);

        System.out.println(start);
        System.out.println(destination);

        return new ResponseEntity<String>("Routing from " + start + " to " + destination, HttpStatus.OK);
    }

}