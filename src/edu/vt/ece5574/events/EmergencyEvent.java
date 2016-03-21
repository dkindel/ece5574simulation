package edu.vt.ece5574.events;

import edu.vt.ece5574.sim.Simulation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sim.engine.*;

public abstract class EmergencyEvent implements Steppable{

	private static final long serialVersionUID = 1;

	protected String robotIDToHandle;
	protected int room;
	protected int floor;
	protected int x_pos;
	protected int y_pos;
	protected int severity; 
	protected String type;

	/**
	 * Can be used for a dummy event.
	 */
	public void step(SimState state_) {
		System.out.println("EmergencyEvent step function called.");
		System.out.println("We're going to send this off to the first robot in the system.");
		
	}
	
	protected void getBaseDetails(String details){
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(details);
			JSONObject json = (JSONObject) obj;
			System.out.println(json.get("type"));
		}catch (ParseException e){
			System.err.println("position: " + e.getPosition());
			System.err.println(e);
		}
	}
	
	/**
	 * Gets the ID of the robot who will handle this event
	 * @return The ID of the handling robot.
	 */
	public String getRobotIDToHandle(){
		return robotIDToHandle;
	}
	
	/**
	 * Gets the type of emergency this is.  Water leak, fire, intruder, etc.
	 * @return The emergency type by string
	 */
	public String getEmergencyType(){
		return type;
	}
}
