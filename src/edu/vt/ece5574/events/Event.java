package edu.vt.ece5574.events;


import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * The abstract class  for all events to inherit
 * @author David Kindel
 *
 */
public abstract class Event {


	protected String eventID = "-1"; 
	protected LinkedList<String> agentsToAccept = null;
	protected long room = -1;
	protected int floor = -1;
	protected int x_pos = -1;
	protected int y_pos = -1;
	protected int severity = -1; 
	protected String type = null;
	protected String action = null;
	protected String message = null;
	protected String building = "-1";
	protected String newAgentID = "-1";
	
	public Event(){
		agentsToAccept = new LinkedList<String>();
	}
	


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
			System.out.println(json);

			JSONArray ids = (JSONArray) json.get("id");
			if(ids != null){
				for(int i = 0 ; i < ids.size(); i++){
					String id = (String) ids.get(i);
					agentsToAccept.add(id);
				}
			}
			System.out.println(json);

			json = (JSONObject) json.get("message");
			
			System.out.println(json);
			
			attr = json.get("msg_type");
			if(attr == null){
				System.err.println("Missing required type.");
				return false;
			}
			type = ((String) attr).toLowerCase();
			
			
			JSONObject body = (JSONObject) json.get("body");
			attr = body.get("building");
			if(attr != null){
				building = (String) attr;
			}

			attr = body.get("room");
			if(attr != null){
				room = (int) (long) attr;
			}
			
			attr = body.get("floor");
			if(attr != null){
				floor = (int) (long) attr;
			}
			
			attr = body.get("xpos");
			if(attr != null){
				x_pos = (int) (long) attr;
			}
			

			attr = body.get("ypos");
			if(attr != null){
				y_pos = (int) (long) attr;
			}
			
			attr = body.get("severity");
			if(attr != null){
				severity = (int) (long) attr;
			}
			
			attr = body.get("action");
			if(attr != null){
				action = ((String) attr).toLowerCase();
			}

			attr = body.get("message");
			if(attr != null){
				message = (String) attr;
			}
			
		}catch (ParseException e){
			System.err.println("position: " + e.getPosition());
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	/**
	 * Gets all of the IDs of Robots who will need to respond to this event
	 * @return The linked list of robot ids
	 */
	public LinkedList<String> getAgentsToAccept(){
		return agentsToAccept;
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
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * @return the building ID
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * Returns the event ID
	 * @return The event ID
	 */
	public String getEventID(){
		return eventID;
	}

	public String getMessage() {
		return message;
	}
	
	
	public static String getEventType(String details){
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(details);
			JSONObject json = (JSONObject) obj;


			json = (JSONObject) json.get("body");
			json = (JSONObject) json.get("message");
			
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
