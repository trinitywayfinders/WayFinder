package ie.tcd.wayfind.lowlevel;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import ie.tcd.wayfind.lowlevel.request.UserRouteRequest;
import ie.tcd.wayfind.lowlevel.type.TravelMode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LowLevelRouteController.class)
@AutoConfigureMockMvc
public class RouteControllerTests {
	
    @Autowired
    private MockMvc mvc;

    @Test
    public void getRoute() throws Exception {
    	
    	UserRouteRequest usr = new UserRouteRequest("Dublin", "Cork", TravelMode.walking);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(usr);
    	
        mvc.perform(MockMvcRequestBuilders.post("/api/route")
        	    .contentType(MediaType.APPLICATION_JSON)
        	    .content(json)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
