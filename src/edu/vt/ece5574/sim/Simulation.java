package edu.vt.ece5574.sim;

import edu.vt.ece5574.dummyclasses.DummyAgent;
import sim.engine.*;

public class Simulation extends SimState {

	//used to seed the RNG
    private static final long serialVersionUID = 1;

    public Simulation(long seed){
    	super(seed);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


    public void start() {
        super.start();  // very important!  This resets and cleans out the Schedule.
        Configuration config = new Configuration();
        config.load("config.json");
        schedule.scheduleRepeating(new DummyAgent());
    }
}
