package edu.vt.ece5574.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.vt.ece5574.events.WaterLeakEvent;
import edu.vt.ece5574.sim.Simulation;

public class EventTests {

	
	@Before
	public void init(){
		String[] args = new String[2];
		args[0] = "-seed";
		args[1] = "1";
		
		Thread thread = new Thread(new Runnable() {
		    @Override
		    public void run() {
				Simulation.main(args);
		    }
		            
		});
		        
		thread.start();

		System.out.println("after main");
		try {
			Thread.sleep(2000); //wait for simulation to be set up
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void waterleak() {
		WaterLeakEvent event = new WaterLeakEvent(null);
		
	}

}
