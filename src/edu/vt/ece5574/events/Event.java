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
	protected LinkedList<Integer> robotsToAccept = null;
	protected LinkedList<Integer> usersToAccept = null;
	protected long room = -1;
	protected int floor = -1;
	protected int x_pos = -1;
	protected int y_pos = -1;
	protected int severity = -1; 
	protected String type = null;
	protected String action = null;
	protected String message = null;
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
			type = ((String) attr).toLowerCase();
			
			
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
				action = ((String) attr).toLowerCase();
			}

			attr = body.get("message");
			if(attr != null){
				message = (String) attr;
			}

			
			JSONArray robotIDs = (JSONArray) body.get("robots");
			if(robotIDs != null){
				robotsToAccept = new LinkedList<Integer>();
				for(int i = 0 ; i < robotIDs.size(); i++){
					int id = (int)(long) robotIDs.get(i);
					robotsToAccept.add(new Integer(id));
				}
			}

			JSONArray userIDs = (JSONArray) body.get("users");
			if(userIDs != null){
				usersToAccept = new LinkedList<Integer>();
				for(int i = 0; i < userIDs.size(); i++){
					int id = (int)(long) userIDs.get(i);
					usersToAccept.add(new Integer(id));
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
	 * Gets all of the IDs of Robots who will need to respond to this event
	 * @return The linked list of robot ids
	 */
	public LinkedList<Integer> getRobotIDsToAccept(){
		return robotsToAccept;
	}
	
	/**
	 * Gets all of the IDs of Users who will need to respond to this event
	 * @return The linked list of user ids
	 */
	public LinkedList<Integer> getUserIDsToAccept(){
		return usersToAccept;
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
	 * @return the building
	 */
	public int getBuilding() {
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
