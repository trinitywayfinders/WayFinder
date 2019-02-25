package ie.tcd.wayfinder.etcd;

import java.io.IOException;

public class Test {

	/*
	 * Create Directory			PASS
	 * Read Directory			PASS
	 * Delete Directory			PASS - assuming directory empty -> check recursive delete
	 * 
	 * Create Key				PASS
	 * Read Key					PASS
	 * Update Key				PASS - same method as create
	 * Delete Key				PASS - assuming has directory
	 */
	
	public static void main(String [] args) throws IOException {
		
		System.out.println("Running main");
		String getDirectory;
		
		//getDirectory = ETCDLibrary.CreateDirectory("appleDirectory");
		//System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);

		//getDirectory = ETCDLibrary.DeleteDirectory("berryfam");
		//System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
		getDirectory = ETCDLibrary.ReadDirectory("banana");
		System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
		
		//getDirectory = ETCDLibrary.CreateKey("banana", "bananaSUCKS", "bananaVALUEsss");
		//System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
		//getDirectory = ETCDLibrary.DeleteKey("berryfam", "strawberry");
		//System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
	}
}
