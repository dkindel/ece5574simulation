package edu.vt.ece5574.events;

import sim.engine.SimState;

public class WaterLeakEvent extends EmergencyEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

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
		super.getBaseDetails(details);
	}
	
	@Override
	public void step(SimState state_) {
		System.out.println("Water Leak Event");
		super.step(state_);
	}

}
