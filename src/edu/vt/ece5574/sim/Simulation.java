package edu.vt.ece5574.sim;

import java.util.LinkedList;
import java.util.Vector;

import edu.vt.ece5574.dummyclasses.DummyAgent;
import edu.vt.ece5574.dummyclasses.DummyBuilding;
import edu.vt.ece5574.events.Event;
import sim.engine.*;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class Simulation extends SimState {

    private static final long serialVersionUID = 1;
    public Continuous2D room = new Continuous2D(1.0,100,100);
    public int numRobots = 5;
    
    public Configuration config;
    public Vector<DummyBuilding> buildings;
    public Vector<DummyAgent> dummyRobots;
    
    LinkedList<Event> events; 	// For now, events are only added, not removed.  
    							// Likely, the building will cause some cleanup 
    							// once the event is handled 

    public Simulation(long seed){
    	super(seed); //needs to be first line, can't just set seed here
    	
        config = new Configuration();
        config.load("config.json");
        boolean debug = false; //will be read in from config.  If it's a debug, we'll set seed manually
        seed = 5;
        if(debug){
        	if(seed == 0){
        		System.err.println("Seed cannot be set to 0. Running sim with default seed.");
        	}
        	else{
        		setSeed(seed);
            	System.out.println("seed modified to: " + seed());
            	System.out.println("Ignore other message noting the job number and seed value.");
        	}
        }
        buildings = new Vector<DummyBuilding>();
        dummyRobots = new Vector<DummyAgent>();
        events = new LinkedList<Event>();
        
    }
    
	public static void main(String[] args) {
		doLoop(Simulation.class, args);
		System.exit(0);
	}


    public void start() {
        super.start();  // very important!  This resets and cleans out the Schedule.
        
        //clear the room of previous actors
        room.clear();
        
        for(int i = 0; i < numRobots; i++){
        	DummyAgent agent = new DummyAgent();
        	room.setObjectLocation(agent,
        			new Double2D(room.getWidth() * 0.5 + random.nextDouble() - 0.5,
        					room.getHeight() * 0.5 + random.nextDouble() - 0.5));
        	dummyRobots.add(agent);
        	schedule.scheduleRepeating(agent);
        }

        int numBuildings = config.getNumBuildings();
        for(int i = 0; i < numBuildings; i++){
        	buildings.add(new DummyBuilding());
        }
    }
    
    public void incomingEvent(Event event){
    	events.add(event);
    }
    
    /**
     * Gets all of the pending emergency events for the appropriate robot.
     * This is case sensitive so make sure things match
     * @param id The ID of the robot to get events for
     * @return The linked list of events
     */
    public LinkedList<Event> getEventsForRobotID(int id){
    	LinkedList<Event> robotEvents = new LinkedList<Event>();
    	for(int i = 0; i < events.size(); i++){
    		LinkedList<Integer> agentIDs = events.get(i).getAgentIDsToRespond();
    		for(int j = 0; j < agentIDs.size(); j++){
    			if(agentIDs.get(j).intValue() == id){
    	    		robotEvents.add(events.get(i));
    	    		break;
    			}
    		}
    	}
		return robotEvents;
    }
    
}
