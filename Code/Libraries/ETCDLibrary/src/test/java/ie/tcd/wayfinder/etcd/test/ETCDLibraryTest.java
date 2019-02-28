package ie.tcd.wayfinder.etcd.test;

import static org.junit.Assert.assertTrue;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
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
	ETCDLibrary library = new ETCDLibrary();

	@Test
	public void createDirectory() throws IOException {

		String expectedOutput = "{\"action\":\"set\",\"node\":{\"key\":\"/test\",\"dir\":true";

		if (directoryExists(directoryName))
			deleteDirectory(directoryName);

		HttpResponse response = library.CreateDirectory(directoryName);
		String responseString = EntityUtils.toString(response.getEntity());

		assertTrue(responseString.contains(expectedOutput));
	}

	@Test
	public void readDirectory() throws IOException {

		String expectedOutput = "{\"action\":\"get\",\"node\":{\"key\":\"/test\",\"dir\":true";

		if (!directoryExists(directoryName))
			createDirectory(directoryName);

		HttpResponse response = library.ReadDirectory(directoryName);
		String responseString = EntityUtils.toString(response.getEntity());

		assertTrue(responseString.contains(expectedOutput));
	}

	@Test
	public void removeDirectory() throws IOException {

		String expectedOutput = "{\"action\":\"delete\",\"node\":{\"key\":\"/test\",\"dir\":true,\"";

		if (!directoryExists(directoryName))
			createDirectory(directoryName);

		HttpResponse response = library.DeleteDirectory(directoryName);
		String responseString = EntityUtils.toString(response.getEntity());

		assertTrue(responseString.contains(expectedOutput));
	}

	@Test
	public void createKey() throws IOException {
		String expectedOutput = "{\"action\":\"set\",\"node\":{\"key\":\"/test/testKey\",\"value\":\"testValue\"";

		if (keyExists(directoryName, key))
			deleteKey(directoryName, key);

		HttpResponse response = library.CreateKey(directoryName, key, value);
		String responseString = EntityUtils.toString(response.getEntity());

		assertTrue(responseString.contains(expectedOutput));
		deleteKey(directoryName, key);
	}

	@Test
	public void updateKey() throws IOException {
		String expectedOutput = "{\"key\":\"/test/testKey\",\"value\":\"newValue2\"";

		String update = "newValue2";

		if (!keyExists(directoryName, key))
			createKey(directoryName, key, value);

		HttpResponse response = library.UpdateKey(directoryName, key, update);
		String responseString = EntityUtils.toString(response.getEntity());

		assertTrue(responseString.contains(expectedOutput));
		deleteKey(directoryName, key);
	}

	@Test
	public void deleteKey() throws IOException {
		String expectedOutput = "{\"key\":\"/test/testKey\",\"value\":\"testValue\"";

		if (!keyExists(directoryName, key))
			createKey(directoryName, key, value);

		HttpResponse response = library.CreateKey(directoryName, key, value);
		String responseString = EntityUtils.toString(response.getEntity());
    	
    	assertTrue(responseString.contains(expectedOutput));
    	}

	@Test
	public void readKey() throws IOException {
		String expectedOutput = "{\"key\":\"/test/testKey\",\"value\":\"testValue\"";

		if (!keyExists(directoryName, key))
			createKey(directoryName, key, value);

		HttpResponse response = library.CreateKey("test", key, value);
		String responseString = EntityUtils.toString(response.getEntity());
    	
    	assertTrue(responseString.contains(expectedOutput));
    	
		deleteKey(directoryName, key);

	}

	private boolean directoryExists(String directoryName) throws IOException {

		HttpResponse response = library.ReadDirectory(directoryName);
		String responseString = EntityUtils.toString(response.getEntity());
    	
    	if (responseString.contains("{\"action\":\"get\",\"node\":{\"key\":\"/" + directoryName + "\",\"dir\":true"))
			return true;
		return false;
	}

	private boolean keyExists(String directoryName, String key) throws IOException {

		HttpResponse response = library.ReadKey(directoryName, key);
		String responseString = EntityUtils.toString(response.getEntity());
		if (responseString.contains("{\"action\":\"set\",\"node\":{\"key\":\"/" + directoryName + "/" + key + "\""))
			return true;
		return false;
	}

	private void createDirectory(String directoryName) throws IOException {
		library.CreateDirectory(directoryName);
	}

	private void deleteDirectory(String directoryName) throws IOException {
		library.DeleteDirectory(directoryName);
	}

	private void createKey(String directoryName, String key, String value) throws IOException {
		library.CreateKey(directoryName, key, value);
	}

	private void deleteKey(String directoryName, String key) throws IOException {
		library.DeleteKey(directoryName, key);
	}

}
