package edu.vt.ece5574.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.vt.ece5574.events.Event;
import edu.vt.ece5574.events.WaterLeakEvent;
import edu.vt.ece5574.sim.Simulation;

public class EventTests {

	Simulation sim;
	
	@Before
	public void init(){
		/*String[] args = new String[2];
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
		}*/
		sim = new Simulation(1);
	}
	
	
	
	private WaterLeakEvent createWaterLeak() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"room\": 1,"
				+ "\"floor\": 2,"
				+ "\"xpos\": 3,"
				+ "\"ypos\": 4,"
				+ "\"severity\": 5,"
				+ "\"action\": \"fix plumbing\","
				+ "\"id\": \"1\"" //id is the id of the agent to handle the event
				+ "}";
		WaterLeakEvent event = new WaterLeakEvent(details);
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("fix plumbing", event.getAction());
		assertEquals("1", event.getRobotIDToHandle());
		return event;
	}
	
	@Test
	public void insertWaterLeak(){
		WaterLeakEvent event = createWaterLeak();
		sim.incomingEvent(event);
		LinkedList<Event> events = sim.getEventsForRobotID("1");
	}

	@Test
	public void testWaterLeakCreation(){
		createWaterLeak();
	}
}
