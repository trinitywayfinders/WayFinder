package ie.tcd.wayfinder.highlevel.navigation;
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

import ie.tcd.wayfinder.highlevel.navigation.Startup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
@AutoConfigureMockMvc
public class LibraryTest {

	
    @Autowired
    private MockMvc mvc;

    @Test
    public void getRoute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/12/25/destination/46/36/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getRouteEmptyStart() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start///destination/46/46/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void getRouteEmptyDestination() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/34/23/destination///").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test 
    public void getRouteLetterCoordinates() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/123asd4/234asd5/destination/asd/hello/").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
}
    
    @Test 
    public void getRouteDecimalNumber() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/13.4/2.345/destination/35.6346/34.346/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test 
    public void getIllegalLat() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/45/78888/destination/34.6346/3946.346/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test 
    public void getIllegalLng() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/500000/2.345/destination/99999/34.346/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
 
    
    
}