package edu.vt.ece5574.sim;

import edu.vt.ece5574.dummyclasses.DummyAgent;
import sim.engine.*;
import sim.field.continuous.Continuous2D;

public class Simulation extends SimState {

	//used to seed the RNG
    private static final long serialVersionUID = 1;
    public Continuous2D room = new Continuous2D(1.0,100,100);
    public int numRobots = 5;

    public Simulation(long seed){
    	super(seed); //needs to be first line, can't just set seed here
    	
        Configuration config = new Configuration();
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
        room.clear();

        //schedule.scheduleRepeating(new DummyAgent());
    }
}
