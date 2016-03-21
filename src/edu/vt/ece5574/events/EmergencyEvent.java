package edu.vt.ece5574.events;

import edu.vt.ece5574.sim.Simulation;
import sim.engine.*;

public abstract class EmergencyEvent implements Steppable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;


	/**
	 * Can be used for a dummy event.
	 */
	public void step(SimState state_) {
		System.out.println("EmergencyEvent step function called.");
		System.out.println("We're going to send this off to the first robot in the system.");
		
	}
}
