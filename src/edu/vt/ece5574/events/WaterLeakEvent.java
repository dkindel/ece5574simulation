package edu.vt.ece5574.events;

import sim.engine.SimState;

public class WaterLeakEvent extends EmergencyEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	@Override
	public void step(SimState state_) {
		System.out.println("Water Leak Event");
		super.step(state_);
	}

}
