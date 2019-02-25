package ie.tcd.wayfinder.etcd;

import java.io.IOException;

public class Test {

	
	public static void main(String [] args) throws IOException {
		
		System.out.println("Running main");
		String getDirectory;
		//getDirectory = ETCDLibrary.ReadDirectory("testdir");
		
		//System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
		getDirectory = ETCDLibrary.CreateDirectory("testdir222");
		System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
		//getDirectory = ETCDLibrary.CreateKey("testdir33", "key33", "FUCKYOU33");
		
		//System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
		//getDirectory = ETCDLibrary.DeleteKey("testdir2", "testdir2");
		//System.out.println("~~~~~~~~~~~~~~~~"+getDirectory);
		
	}
}
