package ie.tcd.wayfinder.highlevel.navigation;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
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
        mvc.perform(MockMvcRequestBuilders.get("/getRoute/start/start/destination/dest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Routing from start to dest")));
    }
    
    @Test
    public void getRoute_EmptyStart() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getRoute/start//destination/dest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void getRoute_EmptyDestination() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getRoute/start/start/destination/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}