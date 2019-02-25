package ie.tcd.wayfinder.etcd.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ie.tcd.wayfinder.etcd.ETCDLibrary;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ETCDLibrary.class)
@AutoConfigureMockMvc
public class ETCDLibraryTest {

	
	/*
	 * create/remove/read directory
	 * create update delete read key
	 */
	
    @Autowired
    private MockMvc mvc;
    
    @Test
    public void createDirectory() {
    	
    	String directoryName = "test";
    	String expectedOutput = "{\"key\":\"test\"}";
    	
    	
    	String url = "http://etcdExample.com/v2/keys/"+directoryName;
    	
    	String Object = "{'dir':'true'}";
    	

    	
    	String request = Library.PUT(url, Optional.of(Object), Optional.empty());
    	
    	String response = ETCDLibrary.CreateDirectory();
    	assertTrue(response == expectedOutput);
    }
    
    @Test
    public void removeDirectory() {
    	
    	String directoryName = "test";
    	String expectedOutput = "{\"key\":\"test\"}";
    	
    	
    	String url = "http://etcdExample.com/v2/keys/"+directoryName;
    	    	
    	String request = Library.DELETE(url, Optional.empty());
    	
    	String response = ETCDLibrary.DeleteDirectory();
    	assertTrue(response == expectedOutput);
    }
    
    @Test
    public void readDirectory() {
    	
    	String directoryName = "test";
    	String expectedOutput = "{\"key\":\"test\"}";
    	
    	
    	String url = "http://etcdExample.com/v2/keys/"+directoryName;

    	String request = Library.GET(url, Optional.empty());
    	
    	String response = ETCDLibrary.ReadDirectory();
    	assertTrue(response == expectedOutput);
    }
    
    @Test
    public void createKey() {

    	String key = "testKey";
    	String value = "testValue";
    	String expectedOutput = "{\"testKey\":\"testValue\"}";
    	
    	String object = "{'value'='"+value+"'}";
    	
    	String url = "http://etcdExample.com/v2/keys/"+key;

    	String request = Library.PUT(url, Optional.of(object), Optional.empty());
    	
    	String response = ETCDLibrary.CreateKey();
    	assertTrue(response == expectedOutput);
    }
    
    @Test
    public void updateKey() {
    	
    	String key = "testKey";
    	String value = "testValue";
    	String expectedOutput = "{\"testKey\":\"testValue\"}";
    	
    	String object = "{'value'='"+value+"'}";
    	
    	String url = "http://etcdExample.com/v2/keys/"+key;

    	String request = Library.PUT(url, Optional.of(object), Optional.empty());
    	
    	String response = ETCDLibrary.UpdateKey();
    	assertTrue(response == expectedOutput);
    }
    
    @Test
    public void deleteKey() {
    	
    	String key = "testKey";
    	String value = "testValue";
    	String expectedOutput = "{\"testKey\":\"testValue\"}";
    	
    	
    	String url = "http://etcdExample.com/v2/keys/"+key;

    	String request = Library.DELETE(url, Optional.empty());
    	
    	String response = ETCDLibrary.CreateKey();
    	assertTrue(response == expectedOutput);
    }
    
    @Test
    public void readKey() {
    	
    	String key = "testKey";
    	String value = "testValue";
    	String expectedOutput = "{\"testKey\":\"testValue\"}";
    	
    	
    	String url = "http://etcdExample.com/v2/keys/"+key;

    	String request = Library.GET(url, Optional.empty());
    	
    	String response = ETCDLibrary.CreateKey();
    	assertTrue(response == expectedOutput);
    	
    }
}