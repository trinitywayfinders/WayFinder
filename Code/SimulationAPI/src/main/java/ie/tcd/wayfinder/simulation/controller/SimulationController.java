package ie.tcd.wayfinder.simulation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

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
@RequestMapping(path="/")
public class SimulationController {
    @RequestMapping(path="/getSimulation/")
    public ResponseEntity<String> retriveBlock() {
        // Parameter check goes here
        List<List<ResponseClass>> masterList = createData();
        
        return new ResponseEntity<String>(("{"+"\"data\":"+ getRandomValue(masterList).toString()+ "}"), HttpStatus.OK);
    }
    
    private List<ResponseClass> getRandomValue(List<List<ResponseClass>> masterList)
    {
        Random random = new Random();
        return masterList.get(random.nextInt(masterList.size()));
    }
    
    private List<List<ResponseClass>> createData() {
        List<List<ResponseClass>> masterList = new ArrayList<>();
        
        List<ResponseClass> returnResponse1 = new ArrayList<>();
        ResponseClass response11 = new ResponseClass();
        ResponseClass response12 = new ResponseClass();
        ResponseClass response13 = new ResponseClass();
        ResponseClass response14 = new ResponseClass();
        ResponseClass response15 = new ResponseClass();
        ResponseClass response16 = new ResponseClass();
        ResponseClass response17 = new ResponseClass();
        ResponseClass response18 = new ResponseClass();
        ResponseClass response19 = new ResponseClass();
        ResponseClass response10 = new ResponseClass();
        
        
        response11.SetVal(53.358262, -6.194067, "traffic jam");
        response12.SetVal(53.373554, -6.202977, "Tree on the road");
        response13.SetVal(53.409629, -6.254458, "Tree on the road");
        response14.SetVal(53.385273, -6.164857, "Tree on the road");
        response15.SetVal(53.380386, -6.175302, "Tree on the road");
        response16.SetVal(53.377284, -6.188762, "Tree on the road");
        response17.SetVal(53.370387, -6.253821, "Tree on the road");
        response18.SetVal(53.353004, -6.264723, "Tree on the road");
        response19.SetVal(53.344797, -6.252192, "Tree on the road");
        response10.SetVal(53.428962, -6.214220, "Tree on the road");
        
        returnResponse1.add(response11);
        returnResponse1.add(response12);
        returnResponse1.add(response13);
        returnResponse1.add(response14);
        returnResponse1.add(response15);
        returnResponse1.add(response16);
        returnResponse1.add(response17);
        returnResponse1.add(response18);
        returnResponse1.add(response19);
        returnResponse1.add(response10);
        
        
        
        List<ResponseClass> returnResponse2 = new ArrayList<>();
        ResponseClass response21 = new ResponseClass();
        ResponseClass response22 = new ResponseClass();
        ResponseClass response23 = new ResponseClass();
        ResponseClass response24 = new ResponseClass();
        ResponseClass response25 = new ResponseClass();
        ResponseClass response26 = new ResponseClass();
        ResponseClass response27 = new ResponseClass();
        ResponseClass response28 = new ResponseClass();
        ResponseClass response29 = new ResponseClass();
        ResponseClass response20 = new ResponseClass();
        
        
        response21.SetVal(53.428962, -6.214220, "traffic jam");
        response22.SetVal(53.416898, -6.152687, "Tree on the road");
        response23.SetVal(53.447980, -6.160301, "Tree on the road");
        response24.SetVal(53.377073, -6.210996, "Tree on the road");
        response25.SetVal(53.370387, -6.253821, "Tree on the road");
        response26.SetVal(53.353004, -6.264723, "Tree on the road");
        response27.SetVal(53.344797, -6.252192, "Tree on the road");
        response28.SetVal(53.428962, -6.214220, "Tree on the road");
        response29.SetVal(53.416898, -6.152687, "Tree on the road");
        response20.SetVal(53.447980, -6.160301, "Tree on the road");
        
        returnResponse2.add(response21);
        returnResponse2.add(response22);
        returnResponse2.add(response23);
        returnResponse2.add(response24);
        returnResponse2.add(response25);
        returnResponse2.add(response26);
        returnResponse2.add(response27);
        returnResponse2.add(response28);
        returnResponse2.add(response29);
        returnResponse2.add(response20);
        
        
        List<ResponseClass> returnResponse3 = new ArrayList<>();
        ResponseClass response31 = new ResponseClass();
        ResponseClass response32 = new ResponseClass();
        ResponseClass response33 = new ResponseClass();
        ResponseClass response34 = new ResponseClass();
        ResponseClass response35 = new ResponseClass();
        ResponseClass response36 = new ResponseClass();
        ResponseClass response37 = new ResponseClass();
        ResponseClass response38 = new ResponseClass();
        ResponseClass response39 = new ResponseClass();
        ResponseClass response30 = new ResponseClass();
        
        
        response31.SetVal(53.428962, -6.214220, "traffic jam");
        response32.SetVal(53.416898, -6.152687, "Tree on the road");
        response33.SetVal(53.447980, -6.160301, "Tree on the road");
        response34.SetVal(53.340103, -6.248921, "traffic jam");
        response35.SetVal(53.345400, -6.258977, "Tree on the road");
        response36.SetVal(53.304906, -6.245537, "Tree on the road");
        response37.SetVal(53.325010, -6.278812, "traffic jam");
        response38.SetVal(53.318341, -6.242724, "Tree on the road");
        response39.SetVal(52.828814, -8.296364, "Tree on the road");
        response30.SetVal(52.873339, -8.031370, "traffic jam");
        
        returnResponse3.add(response31);
        returnResponse3.add(response32);
        returnResponse3.add(response33);
        returnResponse3.add(response34);
        returnResponse3.add(response35);
        returnResponse3.add(response36);
        returnResponse3.add(response37);
        returnResponse3.add(response38);
        returnResponse3.add(response39);
        returnResponse3.add(response30);
        
        List<ResponseClass> returnResponse4 = new ArrayList<>();
        ResponseClass response41 = new ResponseClass();
        ResponseClass response42 = new ResponseClass();
        ResponseClass response43 = new ResponseClass();
        ResponseClass response44 = new ResponseClass();
        ResponseClass response45 = new ResponseClass();
        ResponseClass response46 = new ResponseClass();
        ResponseClass response47 = new ResponseClass();
        ResponseClass response48 = new ResponseClass();
        ResponseClass response49 = new ResponseClass();
        ResponseClass response40 = new ResponseClass();
        
                
        response41.SetVal(52.828814, -8.296364, "traffic jam");
        response42.SetVal(52.873339, -8.031370, "Tree on the road");
        response43.SetVal(52.672206, -8.518969, "Tree on the road");
        response44.SetVal(52.865213, -7.999309, "Tree on the road");
        response45.SetVal(52.929219, -8.167125, "Tree on the road");
        response46.SetVal(53.304906, -6.245537, "Tree on the road");
        response47.SetVal(53.325010, -6.278812, "Tree on the road");
        response48.SetVal(53.318341, -6.242724, "Tree on the road");
        response49.SetVal(53.380386, -6.175302, "Tree on the road");
        response40.SetVal(52.815911, -8.307479, "Santa on the Road");
        
                      
        returnResponse4.add(response41);
        returnResponse4.add(response42);
        returnResponse4.add(response43);
        returnResponse4.add(response44);
        returnResponse4.add(response45);
        returnResponse4.add(response46);
        returnResponse4.add(response47);
        returnResponse4.add(response48);
        returnResponse4.add(response49);
        returnResponse4.add(response40);
        
        masterList.add(returnResponse1);
        masterList.add(returnResponse2);
        masterList.add(returnResponse3);
        masterList.add(returnResponse4);
        
        return masterList;
        
    }

}
