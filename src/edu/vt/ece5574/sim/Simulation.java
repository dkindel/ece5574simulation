package edu.vt.ece5574.sim;

import java.util.Vector;

import edu.vt.ece5574.dummyclasses.DummyAgent;
import edu.vt.ece5574.dummyclasses.DummyBuilding;
import sim.engine.*;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class Simulation extends SimState {

    private static final long serialVersionUID = 1;
    public Continuous2D room = new Continuous2D(1.0,100,100);
    public int numRobots = 5;
    
    Configuration config;
    Vector<DummyBuilding> buildings;

    public Simulation(long seed){
    	super(seed); //needs to be first line, can't just set seed here
    	
        config = new Configuration();
        config.load("config.json");
        boolean debug = true; //will be read in from config.  If it's a debug, we'll set seed manually
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
        	schedule.scheduleRepeating(agent);
        }

        int numBuildings = config.getNumBuildings();
        for(int i = 0; i < numBuildings; i++){
        	buildings.add(new DummyBuilding());
        }
    }
}
