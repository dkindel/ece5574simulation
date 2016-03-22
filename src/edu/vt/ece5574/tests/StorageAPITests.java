package edu.vt.ece5574.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.vt.ece5574.sim.StorageAPI;

/**
 * Test cases for StorageAPI class
 * 
 * @author Vinit Gala 
 * */

public class StorageAPITest {

	@Test
	public void checkGetRequest() {
		StorageAPI var = new StorageAPI();
		assertEquals(var.getRequest("http://ec2-52-201-238-14.compute-1.amazonaws.com/buildings/"),new String("200"));		
	}
	
	@Test
	public void checkDeleteRequest() {
		StorageAPI var = new StorageAPI();
		assertEquals(var.deleteRequest("http://ec2-52-201-238-14.compute-1.amazonaws.com/buildings/2"),new String("200"));		
	}
	
}
