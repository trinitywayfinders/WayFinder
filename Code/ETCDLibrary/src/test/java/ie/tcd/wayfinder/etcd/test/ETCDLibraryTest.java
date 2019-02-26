package ie.tcd.wayfinder.etcd.test;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ie.tcd.wayfinder.etcd.ETCDLibrary;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ETCDLibrary.class)
@AutoConfigureMockMvc
public class ETCDLibraryTest {
		
	@Autowired
    private MockMvc mvc;
	
	String directoryName = "test";
	String key = "testKey";
	String value = "testValue";
    
    @Test
    public void createDirectory() throws IOException {
    	
    	String expectedOutput = "{\"action\":\"set\",\"node\":{\"key\":\"/test\",\"dir\":true";
    	    	    	    	
    	if(directoryExists(directoryName)) 
    		deleteDirectory(directoryName);
    	
    	String response = ETCDLibrary.CreateDirectory(directoryName);        
    	assertTrue(response.contains(expectedOutput));
    }
        
    @Test
    public void readDirectory() throws IOException {

    	String expectedOutput = "{\"action\":\"get\",\"node\":{\"key\":\"/test\",\"dir\":true";
    	
    	if(!directoryExists(directoryName))
    		createDirectory(directoryName);
    	
    	String response = ETCDLibrary.ReadDirectory(directoryName);
    	assertTrue(response.contains(expectedOutput));
    }
    
    @Test
    public void removeDirectory() throws IOException {
    	
    	String expectedOutput = "{\"action\":\"delete\",\"node\":{\"key\":\"/test\",\"dir\":true,\"";
    	
    	if(!directoryExists(directoryName))
    		createDirectory(directoryName);
    	
    	String response = ETCDLibrary.DeleteDirectory(directoryName);
    	assertTrue(response.contains(expectedOutput));
    }
    
    @Test
    public void createKey() throws IOException {
    	String expectedOutput = "{\"action\":\"set\",\"node\":{\"key\":\"/test/testKey\",\"value\":\"testValue\"";
    	

    	if(keyExists(directoryName, key))
    		deleteKey(directoryName, key);
    	
    	String response = ETCDLibrary.CreateKey(directoryName, key, value);
    	assertTrue(response.contains(expectedOutput));
    	deleteKey(directoryName, key);
    }
    
    @Test
    public void updateKey() throws IOException {
    	String expectedOutput = "{\"key\":\"/test/testKey\",\"value\":\"newValue2\"";
    	
    	String update = "newValue2";
    	
    	if(!keyExists(directoryName, key))
    		createKey(directoryName, key, value);
    	
    	String response = ETCDLibrary.UpdateKey(directoryName, key, update);
    	assertTrue(response.contains(expectedOutput));
    	deleteKey(directoryName, key);
    }
    
    @Test
    public void deleteKey() throws IOException {
    	String expectedOutput = "{\"key\":\"/test/testKey\",\"value\":\"testValue\"";
    	

    	if(!keyExists(directoryName, key))
    		createKey(directoryName, key, value);
    	
    	String response = ETCDLibrary.CreateKey(directoryName, key, value);
    	assertTrue(response.contains(expectedOutput));
    }
    
    @Test
    public void readKey() throws IOException {
    	String expectedOutput = "{\"key\":\"/test/testKey\",\"value\":\"testValue\"";
    	
    	if(!keyExists(directoryName, key))
    		createKey(directoryName, key, value);
    	
    	String response = ETCDLibrary.CreateKey("test", key, value);
    	assertTrue(response.contains(expectedOutput));

    	deleteKey(directoryName, key);
    	
    }
    
    private boolean directoryExists(String directoryName) throws IOException {

    	String response = ETCDLibrary.ReadDirectory(directoryName);
    	if(response.contains("{\"action\":\"get\",\"node\":{\"key\":\"/"+directoryName+"\",\"dir\":true"))
    		return true;
    	return false;
    }
    
    private boolean keyExists(String directoryName, String key) throws IOException {

    	String response = ETCDLibrary.ReadKey(directoryName, key);
    	if(response.contains("{\"action\":\"set\",\"node\":{\"key\":\"/"+directoryName+"/"+key+"\""))
    		return true;
    	return false;
    }
    
    private void createDirectory(String directoryName) throws IOException { 
    	ETCDLibrary.CreateDirectory(directoryName);
    }
    
    private void deleteDirectory(String directoryName) throws IOException { 
    	ETCDLibrary.DeleteDirectory(directoryName);
    }
    
    private void createKey(String directoryName, String key, String value) throws IOException { 
    	ETCDLibrary.CreateKey(directoryName, key, value);
    }
    
    private void deleteKey(String directoryName, String key) throws IOException { 
    	ETCDLibrary.DeleteKey(directoryName, key);
    }
    
    
    
}