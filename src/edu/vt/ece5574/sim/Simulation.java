package edu.vt.ece5574.sim;

import java.util.HashMap;
import java.util.LinkedList;

import edu.vt.ece5574.agents.Agent;
import edu.vt.ece5574.agents.Building;
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
    public HashMap<String, Agent> agents; //map the agent id to the agent itself
    

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
        agents = new HashMap<String, Agent>();
    }
    
	public static void main(String[] args) {
		doLoop(Simulation.class, args);
		System.exit(0);
	}


    public void start() {
        super.start();  // very important!  This resets and cleans out the Schedule.
        
        //clear the room of previous actors
        room.clear();

        int numBuildings = config.getNumBuildings();
        for(int i = 0; i < numRobots; i++){
        	Robot agent = new Robot(room.getWidth() * 0.5 + random.nextDouble() - 0.5,
					room.getHeight() * 0.5 + random.nextDouble() - 0.5,Color.WHITE, new Integer(i+numBuildings).toString(), "0");
        	room.setObjectLocation(agent,
        			new Double2D(room.getWidth() * 0.5 + random.nextDouble() - 0.5,
        					room.getHeight() * 0.5 + random.nextDouble() - 0.5));
        	agents.put(new Integer(i+1).toString(), agent);
        	schedule.scheduleRepeating(agent);
        }

        for(int i = 0; i < numBuildings; i++){
        	agents.put(new Integer(i).toString(), new Building(100, 100, 5, 2, new Integer(i).toString()));
        }
    }
    
    public void incomingEvent(Event event){
    	LinkedList<String> acceptingAgents = event.getAgentsToAccept();
    	for(int i = 0; i < acceptingAgents.size(); i++){
    		agents.get(acceptingAgents.get(i)).addEvent(event);
    	}
    }
    

    /**
     * Get the agent by the provided string ID
     * @param id the id of the agent
     * @return the agent object itself or null if not found
     */
	public Agent getAgentByID(String id) {
		return agents.get(id);
	}
}
