package ie.tcd.wayfinder.simulation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.tcd.wayfinder.simulation.request.*;

import ie.tcd.wayfinders.restLibrary.Library;

@RestController
public class SimulationController {
    @RequestMapping(path="/getSimulation")
    public ResponseEntity<String> retriveBlock() {
        // Parameter check goes here
        
        ResponseClass[] responseBody = new ResponseClass[5];
        responseBody[0].SetVal(53.344855, -6.253051, "traffic jam");
        responseBody[1].SetVal(53.379669, -6.090805, "Tree on the road");
        responseBody[2].SetVal(53.347776, -6.258138, "Blocked Road");
        responseBody[3].SetVal(53.353269, -6.318516, "Deer Safari Party");
        responseBody[4].SetVal(53.345593, -6.194612, "Fish on the road");
       
        
        return new ResponseEntity<String>(responseBody.toString(), HttpStatus.OK);
    }

}
