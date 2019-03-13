package ie.tcd.wayfinder.highlevel.navigation;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ie.tcd.wayfinder.highlevel.navigation.Startup;
import ie.tcd.wayfinders.restLibrary.Library;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
@AutoConfigureMockMvc(secure = false)
public class LibraryTest {
	@Value("${security.oauth2.client.accessTokenUri}")
	private String accessTokenUri;
	
	@Autowired
	private MockMvc mvc;
	
	private String accessToken;
	
	@Before
	public void initialized() {
    	try {
			accessToken = obtainAccessToken("nicky", "pasword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void getAccessToken() throws Exception {
//		accessToken = obtainAccessToken("nicky", "pasword");
//	}
	
    @Test
    public void getRoute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/53.348/-6.249/destination/46/36/transit")
        		.header("Authorization", "Bearer " + accessToken)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getRouteEmptyStart() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start///destination/53.348/-6.249/transit")
        		.header("Authorization", "Bearer " + accessToken)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void getRouteEmptyDestination() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/53.348/-6.249/destination///transit")
        		.header("Authorization", "Bearer " + accessToken)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test 
    public void getRouteLetterCoordinates() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/123asd4/234asd5/destination/asd/hello/transit")
        		.header("Authorization", "Bearer " + accessToken)
        		.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isBadRequest());
}
    
    @Test 
    public void getRouteDecimalNumber() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/13.4/2.345/destination/35.6346/34.346/transit")
        		.header("Authorization", "Bearer " + accessToken)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test 
    public void getIllegalLat() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/45/78888/destination/34.6346/3946.346/transit")
        		.header("Authorization", "Bearer " + accessToken)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test 
    public void getIllegalLng() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/navigation/start/500000/2.345/destination/99999/34.346/transit")
        		.header("Authorization", "Bearer " + accessToken)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	private String obtainAccessToken(String username, String password) throws Exception {
		String boundary = "--------------------------" + UUID.randomUUID().toString();
		
		MultipartEntityBuilder multipartsBuilder = MultipartEntityBuilder.create();
		multipartsBuilder.addTextBody("grant_type", "password");
		multipartsBuilder.addTextBody("client_id", "web-app");
		multipartsBuilder.addTextBody("username", username);
		multipartsBuilder.addTextBody("password", password);
		
		multipartsBuilder.setBoundary(boundary);
		
	    String multiparts = EntityUtils.toString(multipartsBuilder.build());

	    Map<String, String> headers = new HashMap<>();
	    headers.put("Content-Type", ContentType.MULTIPART_FORM_DATA.getMimeType()+ ";boundary=" + boundary);
	    headers.put("Accept", "application/json;charset=UTF-8");
	    headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString("web-app:123456".getBytes()));
	    	    	 
//	    ResultActions result 
//	      = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/oauth/token")
//	        .params(params)
//	        .contentType(MediaType.MULTIPART_FORM_DATA)
//	        .with(httpBasic("web-app","123456"))
//	        .accept("application/json;charset=UTF-8"))
//	        .andExpect(status().isOk())
//	        .andExpect(content().contentType("application/json;charset=UTF-8"));
//	 
//	    String resultString = result.andReturn().getResponse().getContentAsString();
//	    	 
//	    JacksonJsonParser jsonParser = new JacksonJsonParser();
//	    return jsonParser.parseMap(resultString).get("access_token").toString();
	    HttpResponse response = Library.POST(accessTokenUri, Optional.of(headers), Optional.of(multiparts));
	    
	    int statusCode = response.getStatusLine().getStatusCode();
	    String resultString = EntityUtils.toString(response.getEntity());
	    
	    Assert.assertEquals(200, statusCode);
	    
	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    return jsonParser.parseMap(resultString).get("access_token").toString();
	}
}