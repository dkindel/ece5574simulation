package edu.vt.ece5574.events;

public class WaterLeakEvent extends EmergencyEvent{


	String message; 
	/**
	 * Build the WaterLeakEvent with the provided details.  
	 * Details is the JSON string of the body of a message from the push team and
	 * looks like this:
	 * "{
	 *   "room": int,
	 *   "floor": int,
	 *   etc.
	 * }"
	 * 
	 */
	public WaterLeakEvent(String details){
		super.setBaseDetails(details, "water leak");
	}
	

}
