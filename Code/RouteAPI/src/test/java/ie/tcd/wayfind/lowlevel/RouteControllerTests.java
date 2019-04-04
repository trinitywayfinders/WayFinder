package ie.tcd.wayfind.lowlevel;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.tcd.wayfind.lowlevel.controller.LowLevelRouteController;
import ie.tcd.wayfind.lowlevel.request.InputUserRouteRequest;
import ie.tcd.wayfind.lowlevel.request.UserRouteRequest;
import ie.tcd.wayfind.lowlevel.type.TravelMode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LowLevelRouteController.class)
@AutoConfigureMockMvc
public class RouteControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LowLevelRouteController controller;
    
    @Test
    public void getRoute() throws Exception {

    	InputUserRouteRequest usr = new InputUserRouteRequest("Dublin", "Temple Bar", TravelMode.walking,"Zihan");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(usr);

        mvc.perform(MockMvcRequestBuilders.post("/api/route/")
        	    .contentType(MediaType.APPLICATION_JSON)
        	    .content(json)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void checkRouteReturned() {
    	
    	UserRouteRequest usr = new UserRouteRequest("Dublin", "Cork", TravelMode.walking);
    
    	
    	String response;
		try {
			response = EntityUtils.toString(controller.getRoute(usr, false).getEntity(), "UTF-8");
			JSONArray routes  = new JSONObject(response).getJSONArray("routes");
	    	for(int i = 0; i < routes.length(); i++) {
				JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");
				
				for(int j = 0; j < legs.length(); j++) {
					JSONObject leg = legs.getJSONObject(j);

					String startLocation = leg.getString("start_address");
					String endLocation = leg.getString("end_address");

					assertTrue(startLocation.equals("Dublin, Ireland") && endLocation.equals("Cork, Ireland"));
				}
			}
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test 
    public void checkDistanceOver3() {
    	UserRouteRequest usr = new UserRouteRequest("Dublin", "Cork", TravelMode.walking);
            	
    	String response;
		try {
			response = EntityUtils.toString(controller.getRoute(usr, false).getEntity(), "UTF-8");
	    	assertTrue(controller.getTotalDistanceRoute(response) > 3000);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
