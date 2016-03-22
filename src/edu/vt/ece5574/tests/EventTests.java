package edu.vt.ece5574.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.vt.ece5574.events.*;
import edu.vt.ece5574.sim.Simulation;

/**
 * Test events and see that they get set properly, get added to the event list, etc.
 * @author David Kindel
 *
 */
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
	
	
	
	private FireEvent createFire() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
				+ "\"msg_type\": \"fire\","
				+ "\"body\": {"
				+ "\"building\": 0,"
				+ "\"room\": 1,"
				+ "\"floor\": 2,"
				+ "\"xpos\": 3,"
				+ "\"ypos\": 4,"
				+ "\"severity\": 5,"
				+ "\"action\": \"Extinguish\","
				+ "\"robots\": [0,1]" //id is the id of the agent to handle the event
				+ "}"
				+ "}"
				+ "}";
		System.out.println(details);
		assertEquals("fire", Event.getEventType(details));
		FireEvent event = new FireEvent();
		assertTrue(event.init(details));
		assertEquals("fire", event.getEventType());
		assertEquals(0, event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("Extinguish", event.getAction());
		return event;
	}
	
	@Test
	public void insertFire(){
		FireEvent event = createFire();
		sim.incomingEvent(event);
		LinkedList<Event> events = sim.getEventsForRobotID(1);
		assertEquals("0", events.get(0).getEventID());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void insertFireGetOOBEvent(){
		FireEvent event = createFire();
		sim.incomingEvent(event);
		LinkedList<Event> events = sim.getEventsForRobotID(1);
		assertEquals("0", events.get(0).getEventID());
		events.get(1);
	}
	
	

	@Test
	public void testFireCreation(){
		createFire();
	}
}
