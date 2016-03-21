package edu.vt.ece5574.events;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public abstract class Event {


	protected String robotIDToHandle;
	protected long room;
	protected int floor;
	protected int x_pos;
	protected int y_pos;
	protected int severity; 
	protected String type;
	protected String action;

	protected void setBaseDetails(String details, String type_){
		type = type_;
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(details);
			JSONObject json = (JSONObject) obj;
			System.out.println(json.get("room"));
			room = (int) (long) json.get("room");
			floor = (int) (long) json.get("floor");
			x_pos = (int) (long) json.get("xpos");
			y_pos = (int) (long) json.get("ypos");
			severity = (int) (long) json.get("severity");
			action = (String) json.get("action");
			robotIDToHandle = (String) json.get("id");
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

	/**
	 * @return the room
	 */
	public long getRoom() {
		return room;
	}

	/**
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * @return the x_pos
	 */
	public int getX_pos() {
		return x_pos;
	}

	/**
	 * @return the y_pos
	 */
	public int getY_pos() {
		return y_pos;
	}

	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return severity;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
}
