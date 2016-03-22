package edu.vt.ece5574.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.vt.ece5574.agents.User;

/**
 * Test cases for Users class
 * 
 * @author Vedahari Narasimhan Ganesan 
 * */

public class UserTest {

	@Test
	public void checkDefaultBuildingId() {
		User user = new User();
		assertEquals(user.getBuildingID(),User.DEFAULT_BUILDING);		
	}
	
	@Test
	public void checkBuildingId() {
		User user = new User();
		user.setBuildingID(5000);
		assertEquals(user.getBuildingID(),5000);		
	}
	
	@Test
	public void checkDefaultAppUser() {
		User user = new User();
		assertEquals(user.isAppUser(),false);		
	}
	
	@Test
	public void checkAppUser() {
		User user = new User();
		user.setAppUser(true);
		assertEquals(user.isAppUser(),true);		
	}
	
	@Test
	public void checkDefaultUser() {
		int id = 5;
		User user = new User(id);
		assertEquals(user.getUserID(), id);
		assertEquals(user.getBuildingID(),User.DEFAULT_BUILDING);
		assertEquals(user.isAppUser(),false);
	}

}
