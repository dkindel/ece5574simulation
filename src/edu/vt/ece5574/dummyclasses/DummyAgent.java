package edu.vt.ece5574.dummyclasses;

import java.util.LinkedList;

import edu.vt.ece5574.agents.AbstractAgent;
import edu.vt.ece5574.events.Event;
import edu.vt.ece5574.events.FireEvent;
import edu.vt.ece5574.events.WaterLeakEvent;
import edu.vt.ece5574.sim.Simulation;
import sim.engine.*;

/**
 * A class to simply inherit the abstract agent to be instatiated.  This agent is awfully dumb
 * @author David Kindel
 *
 */
public class DummyAgent extends AbstractAgent {

	private static final long serialVersionUID = 1;

	public DummyAgent(){
		super(-2);
	}
	
	public DummyAgent(int id){
		super(id);
	}
	
	@Override
	public void step(SimState state_) {
		Simulation state = (Simulation) state_;
		handleEvents(state);
	}

	public void handleEvents(Simulation state){
		LinkedList<Event> events = state.getEventsForRobotID(super.id);
		if(!events.isEmpty()){
			for(int i = 0; i < events.size(); i++){
				if(events.get(i).getEventType().toLowerCase().equals("fire")){
					FireEvent fire = (FireEvent) events.get(i);
					System.out.println("Handling fire id " + fire.getEventID());
				}
				else if(events.get(i).getEventType().toLowerCase().equals("water leak")){
					WaterLeakEvent leak = (WaterLeakEvent) events.get(i);
					System.out.println("Handling water leak id " + leak.getEventID());
				}
			}
		}
	}
}
