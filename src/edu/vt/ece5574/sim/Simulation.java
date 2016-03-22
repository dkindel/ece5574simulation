package edu.vt.ece5574.sim;

import java.util.LinkedList;
import java.util.Vector;

import edu.vt.ece5574.agents.Robot;
import edu.vt.ece5574.events.Event;
import sim.engine.*;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;
import java.awt.*;

/**
 * The root of the simulation.  This is where things get started and the magic happens.
 * @author David Kindel
 *
 */
public class Simulation extends SimState {

    private static final long serialVersionUID = 1;
    public Continuous2D room = new Continuous2D(1.0,100,100);
    public int numRobots = 5;
    
    public Configuration config;
    public Vector<Building> buildings;
    //public Vector<DummyAgent> dummyRobots;
    public Vector<Robot> Robots;
    
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
        buildings = new Vector<Building>();
        Robots = new Vector<Robot>();
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
        	Robot agent = new Robot(room.getWidth() * 0.5 + random.nextDouble() - 0.5,
					room.getHeight() * 0.5 + random.nextDouble() - 0.5,Color.WHITE,i);
        	room.setObjectLocation(agent,
        			new Double2D(room.getWidth() * 0.5 + random.nextDouble() - 0.5,
        					room.getHeight() * 0.5 + random.nextDouble() - 0.5));
        	Robots.add(agent);
        	schedule.scheduleRepeating(agent);
        }

        int numBuildings = config.getNumBuildings();
        for(int i = 0; i < numBuildings; i++){
        	buildings.add(new Building(100, 100, 5, 2));
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
    		LinkedList<Integer> robotIDs = events.get(i).getRobotIDsToAccept();
    		if(robotIDs == null){
    			continue;
    		}
    		for(int j = 0; j < robotIDs.size(); j++){
    			if(robotIDs.get(j).intValue() == id){
    	    		robotEvents.add(events.get(i));
    	    		break;
    			}
    		}
    	}
		return robotEvents;
    }
    
    /**
     * Gets all of the pending emergency events for the appropriate user.
     * This is case sensitive so make sure things match
     * @param id The ID of the user to get events for
     * @return The linked list of events
     */
    public LinkedList<Event> getEventsForUserID(int id){
    	LinkedList<Event> robotEvents = new LinkedList<Event>();
    	for(int i = 0; i < events.size(); i++){
    		LinkedList<Integer> userIDs = events.get(i).getUserIDsToAccept();
    		if(userIDs == null){
    			continue;
    		}
    		for(int j = 0; j < userIDs.size(); j++){
    			if(userIDs.get(j).intValue() == id){
    	    		robotEvents.add(events.get(i));
    	    		break;
    			}
    		}
    	}
		return robotEvents;
    }
}
