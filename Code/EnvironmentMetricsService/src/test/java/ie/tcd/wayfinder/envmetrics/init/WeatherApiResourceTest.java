package ie.tcd.wayfinder.envmetrics.init;

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
@AutoConfigureMockMvc(secure = true)

public class WeatherApiResourceTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testApiPositive() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/environment/weather/12.34/34.45")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}