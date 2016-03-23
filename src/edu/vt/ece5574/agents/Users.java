package edu.vt.ece5574.agents;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * This class provides required information
 * about all the users in the simulated environment.
 * @author Vedahari Narasimhan Ganesan
 * */

public class Users {
	LinkedList<User> userList;
	
	public Users(){
		userList = new LinkedList<User>();
	}
	
	public LinkedList<User> getAllUsers()
	{
		return userList;
	}
	
	public User getUser(int id)
	{		
		for (int i=0; i<userList.size();++i)
		{
			if(userList.get(i).getUserID()==id)
			{
				//The building is found
				return userList.get(i);
			}
		}
		return null;
	}
	
	public void createNewUser()
	{
		
		userList.add(new User(0));
	}
	
	public boolean isUserExisting(int id)
	{		
		for (int i=0; i<userList.size();++i)
		{
			if(userList.get(i).getUserID()==id)
			{
				//The building is found
				return true;
			}
		}
		return false;
	}
	
	public void removeAllUsers()
	{		
		userList.clear();
	}	
	
	public void removeUser(int id)
	{
		for (int i=0; i<userList.size();++i)
		{
			if(userList.get(i).getUserID()==id)
			{
				//The building is found
				userList.remove(i);
			}
		}		
	}
	
	public ArrayList<Integer> getAppInstalledUsersID()
	{
		ArrayList<Integer> arrAppInstalledUsers = new ArrayList<Integer>();
		for (int i=0;i<userList.size();++i)
		{
			if (userList.get(i).isAppUser())
			{
				int userID = userList.get(i).getUserID();
				arrAppInstalledUsers.add(userID);
			}
		}		
		return arrAppInstalledUsers;		
	}
	
	public ArrayList<Integer> getUsersInBuilding(int buildingID)
	{
		ArrayList<Integer> arrUserID = new ArrayList<Integer>();
		for (int i=0;i<userList.size();++i)
		{
			if (userList.get(i).getBuildingID() == buildingID)
			{
				int userID = userList.get(i).getUserID();
				arrUserID.add(userID);
			}
		}		
		return arrUserID;		
	}
	
	public int getNumberOfUsers()
	{		
		return userList.size();
	}
}
