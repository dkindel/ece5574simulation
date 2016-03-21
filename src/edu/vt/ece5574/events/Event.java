package edu.vt.ece5574.events;


import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public abstract class Event {


	protected String eventID = "-1"; 
	protected LinkedList<Integer> agentsToRespond = null;
	protected long room = -1;
	protected int floor = -1;
	protected int x_pos = -1;
	protected int y_pos = -1;
	protected int severity = -1; 
	protected String type = null;
	protected String action = null;
	protected int building = -1;
	
	

	protected boolean setBaseDetails(String details){
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(details);
			JSONObject json = (JSONObject) obj;


			Object attr = json.get("messageId");
			if(attr == null){
				System.err.println("Missing required event ID.");
				return false;
			}
			eventID = (String) attr;
			
			json = (JSONObject) json.get("body");
			
			attr = json.get("msg_type");
			if(attr == null){
				System.err.println("Missing required type.");
				return false;
			}
			type = (String) attr;
			
			
			JSONObject body = (JSONObject) json.get("body");
			attr = body.get("building");
			if(attr == null){
				System.err.println("Missing required building number.");
				return false;
			}
			building = (int) (long) attr;

			attr = body.get("room");
			if(attr == null){
				System.err.println("Missing required room number.");
				return false;
			}
			room = (int) (long) attr;
			
			attr = body.get("floor");
			if(attr == null){
				System.err.println("Missing required floor number.");
				return false;
			}
			floor = (int) (long) attr;
			
			attr = body.get("xpos");
			if(attr == null){
				System.err.println("Missing required xpos number.");
				return false;
			}
			x_pos = (int) (long) attr;
			

			attr = body.get("ypos");
			if(attr == null){
				System.err.println("Missing required ypos number.");
				return false;
			}
			y_pos = (int) (long) attr;
			
			attr = body.get("severity");
			if(attr == null){
				System.err.println("Missing required severity number.");
				return false;
			}
			severity = (int) (long) attr;
			
			attr = body.get("action");
			if(attr != null){
				action = (String) attr;
			}
			
			JSONArray robotIDs = (JSONArray) body.get("robots");
			if(robotIDs != null){
				agentsToRespond = new LinkedList<Integer>();
				for(int i = 0 ; i < robotIDs.size(); i++){
					System.out.println(robotIDs.get(i));
					int id = (int)(long) robotIDs.get(i);
					agentsToRespond.add(new Integer(id));
				}
			}
			//robotIDs.get(0);
		}catch (ParseException e){
			System.err.println("position: " + e.getPosition());
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	/**
	 * Gets all of the IDs of Agents who will need to respond to this event
	 * @return The linked list of agent ids
	 */
	public LinkedList<Integer> getAgentIDsToRespond(){
		return agentsToRespond;
	}
	
	/**
	 * Gets the type of emergency this is.  Water leak, fire, intruder, etc.
	 * @return The emergency type by string
	 */
	public String getEventType(){
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
	
	public boolean mustBeRespondedTo(){
		return false;
	}
	
	/**
	 * Returns the event ID
	 * @return The event ID
	 */
	public String getEventID(){
		return eventID;
	}
	
	public static String getEventType(String details){
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(details);
			JSONObject json = (JSONObject) obj;


			json = (JSONObject) json.get("body");
			
			Object attr = json.get("msg_type");
			if(attr == null){
				System.err.println("Missing required type.");
			}
			else{
				return (String) attr;
			}

		}catch (ParseException e){
			System.err.println("position: " + e.getPosition());
			System.err.println(e);
		}
		return null;
	}
}
