package edu.vt.ece5574.agents;

import java.util.LinkedList;
import java.util.Random;

import edu.vt.ece5574.events.Event;
import edu.vt.ece5574.events.FireEvent;
import edu.vt.ece5574.events.IntruderEvent;
import edu.vt.ece5574.events.WaterLeakEvent;
import edu.vt.ece5574.sim.Simulation;
import sim.engine.SimState;
import sim.engine.Steppable;


/**
 * Class for representing User/People in the 
 * simulation environment. 
 * @author Vedahari Narasimhan Ganesan 
 */

public class User implements Steppable {
	
	LinkedList<Event> userEvents;
	private static final long serialVersionUID = 1;
	private static int userID;
	private int buildingID;
	public static final int DEFAULT_BUILDING = 0;
	private boolean isAppUser;
	private int maxUsers = Integer.MAX_VALUE;
	private Random rand;
	
	public void handleUserEvents(){
		while(userEvents.size()!=0)
		{
			Event currentEvent = userEvents.removeFirst();
			if(currentEvent instanceof IntruderEvent){
				//Evaluate this is caused by this user
			}
			else if(currentEvent instanceof WaterLeakEvent){			
				//If needed handle this user event.
			}
			else if(currentEvent instanceof FireEvent){
				//If needed handle this user event.
			}
				
		}
	}
	
	public User(int usrID, int building, boolean bAppUser){
		userID = usrID;
		buildingID = building;
		isAppUser = bAppUser;
	}
	
	public User(int usrID, int building){
		userID = usrID;
		buildingID = building;
		isAppUser = false;
	}
	
	public User(int usrID){
		userID = usrID;
		buildingID = DEFAULT_BUILDING;
		isAppUser = false;
	}
	
	public User()
	{
		rand = new Random();
		userID = rand.nextInt(maxUsers);
		buildingID = DEFAULT_BUILDING;
		isAppUser = false;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public int getBuildingID()
	{
		return buildingID;
	}
	
	public void setBuildingID(int id)
	{
		buildingID = id;
	}
	
	public boolean isAppUser(){
		return isAppUser;
	}	
	
	public void setAppUser(boolean bAppUser){
		isAppUser = bAppUser;
	}
	
	public void createRandomEvent()
	{
		//TODO: Create random event that is to be handled by the system
	}	
	
	@Override
	public void step(SimState state) {
		// TODO Auto-generated method stub
		Simulation simState = (Simulation)state;		
		userEvents.addAll(simState.getEventsForRobotID(userID));
		if(userEvents.isEmpty()){
			//if no event, create event for the robots to handle
			createRandomEvent();
		}
		else{
			//in case of events react
			handleUserEvents();
		}		
	}
}
