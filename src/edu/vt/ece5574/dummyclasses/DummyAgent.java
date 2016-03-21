package edu.vt.ece5574.dummyclasses;

import java.util.LinkedList;

import edu.vt.ece5574.agents.AbstractAgent;
import edu.vt.ece5574.events.EmergencyEvent;
import edu.vt.ece5574.events.FireEvent;
import edu.vt.ece5574.sim.Simulation;
import sim.engine.*;

public class DummyAgent extends AbstractAgent {

	private static final long serialVersionUID = 1;

	public DummyAgent(){
		super.id = "dummy";
	}
	
	@Override
	public void step(SimState state_) {
		Simulation state = (Simulation) state_;
		handleEvents(state);
	}

	public void handleEvents(Simulation state){
		LinkedList<EmergencyEvent> events = state.getEventsForRobotID(super.id);
		if(!events.isEmpty()){
			for(int i = 0; i < events.size(); i++){
				if(events.get(i).getEmergencyType().toLowerCase().equals("fire")){
					FireEvent fire = (FireEvent) events.get(i);
					System.out.println(fire.toString());
				}
			}
		}
	}
}
