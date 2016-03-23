package edu.vt.ece5574.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.vt.ece5574.agents.Agent;
import edu.vt.ece5574.agents.Building;
import edu.vt.ece5574.agents.Robot;
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
		sim.addAgent(new Building(100, 100, 2, 4, "0"));
		sim.addAgent(new Robot("1", "0"));
		sim.addAgent(new Robot("10", "0"));
		sim.addAgent(new Robot("5", "0"));
		sim.addAgent(new Robot("2", "0"));
	}
	
	
	
	private FireEvent createFire() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"2\", \"1\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"fire\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"room\": 1,"
							+ "\"floor\": 2,"
							+ "\"xpos\": 3,"
							+ "\"ypos\": 4,"
							+ "\"severity\": 5,"
							+ "\"action\": \"Extinguish\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("fire", Event.getEventType(details));
		FireEvent event = new FireEvent();
		assertTrue(event.init(details));
		assertEquals("fire", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("extinguish", event.getAction());
		return event;
	}
	
	private FireEvent createFireBadRobotID(){
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"3\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"fire\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"room\": 1,"
							+ "\"floor\": 2,"
							+ "\"xpos\": 3,"
							+ "\"ypos\": 4,"
							+ "\"severity\": 5,"
							+ "\"action\": \"Extinguish\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("fire", Event.getEventType(details));
		FireEvent event = new FireEvent();
		assertTrue(event.init(details));
		assertEquals("fire", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("extinguish", event.getAction());
		return event;
	}
	
	
	private FireEvent createFireBadAndGoodRobotID(){
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"3\", \"1\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"fire\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"room\": 1,"
							+ "\"floor\": 2,"
							+ "\"xpos\": 3,"
							+ "\"ypos\": 4,"
							+ "\"severity\": 5,"
							+ "\"action\": \"Extinguish\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("fire", Event.getEventType(details));
		FireEvent event = new FireEvent();
		assertTrue(event.init(details));
		assertEquals("fire", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("extinguish", event.getAction());
		return event;
	}
	
	private WaterLeakEvent createWaterLeak() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"1\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"water leak\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"room\": 1,"
							+ "\"floor\": 2,"
							+ "\"xpos\": 3,"
							+ "\"ypos\": 4,"
							+ "\"severity\": 5,"
							+ "\"action\": \"fix plumbing\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("water leak", Event.getEventType(details));
		WaterLeakEvent event = new WaterLeakEvent();
		assertTrue(event.init(details));
		assertEquals("water leak", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("fix plumbing", event.getAction());
		return event;
	}
	
	private IntruderEvent createIntruder() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"1\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"intruder\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"room\": 1,"
							+ "\"floor\": 2,"
							+ "\"xpos\": 3,"
							+ "\"ypos\": 4,"
							+ "\"severity\": 5,"
							+ "\"action\": \"defend\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("intruder", Event.getEventType(details));
		IntruderEvent event = new IntruderEvent();
		assertTrue(event.init(details));
		assertEquals("intruder", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("defend", event.getAction());
		return event;
	}
	
	private MoveRobotEvent createMoveRobot() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"1\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"move robot\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"room\": 1,"
							+ "\"floor\": 2,"
							+ "\"xpos\": 3,"
							+ "\"ypos\": 4,"
							+ "\"severity\": 5,"
							+ "\"action\": \"move\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("move robot", Event.getEventType(details));
		MoveRobotEvent event = new MoveRobotEvent();
		assertTrue(event.init(details));
		assertEquals("move robot", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("move", event.getAction());
		return event;
	}
	
	private WaterLeakEvent createWaterLeakForUser() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"5\",\"10\",\"1\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"water leak\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"room\": 1,"
							+ "\"floor\": 2,"
							+ "\"xpos\": 3,"
							+ "\"ypos\": 4,"
							+ "\"severity\": 5,"
							+ "\"message\": \"There was a water leak in the building\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("water leak", Event.getEventType(details));
		WaterLeakEvent event = new WaterLeakEvent();
		assertTrue(event.init(details));
		assertEquals("water leak", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals(1, event.getRoom());
		assertEquals(2, event.getFloor());
		assertEquals(3, event.getX_pos());
		assertEquals(4, event.getY_pos());
		assertEquals(5, event.getSeverity());
		assertEquals("There was a water leak in the building", event.getMessage());
		return event;
	}
	
	private UserMessageEvent createUserMessage() {
		//Simulate what we'd get on the response of the push request
		String details = 
				"{"
				+ "\"messageId\": \"0\","
				+ "\"body\":{"
					+ "\"id\": [\"4\",\"10\",\"1\"]," //id is the id of the agent to handle the event
					+ "\"message\": {"
						+ "\"msg_type\": \"message\","
						+ "\"body\": {"
							+ "\"building\": \"0\","
							+ "\"message\": \"Electricity will be off between 9am-5pm.\""
							+ "}"
						+ "}"
					+ "}"
				+ "}";
		assertEquals("message", Event.getEventType(details));
		UserMessageEvent event = new UserMessageEvent();
		assertTrue(event.init(details));
		assertEquals("message", event.getEventType());
		assertEquals("0", event.getBuilding());
		assertEquals("Electricity will be off between 9am-5pm.", event.getMessage());
		return event;
	}
	
	
	@Test
	public void insertFire(){
		FireEvent event = createFire();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("1");
		assertEquals("0", agent.getEventList().get(0).getEventID());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void insertFireGetOOBEvent(){
		FireEvent event = createFire();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("1");
		LinkedList<Event> events = agent.getEventList();
		assertEquals("0", events.get(0).getEventID());
		agent = sim.getAgentByID("5");
		assertEquals("0", events.get(0).getEventID());
		events.get(1);
	}
	
	
	@Test
	public void testFireCreation(){
		createFire();
	}
	
	
	@Test
	public void testWaterLeakCreation(){
		createWaterLeak();
	}
	
	@Test
	public void insertWaterLeak(){
		WaterLeakEvent event = createWaterLeak();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("1");
		LinkedList<Event> events = agent.getEventList();
		assertEquals("0", events.get(0).getEventID());
	}
	
	@Test
	public void checkNoEventsBadRobotID(){
		WaterLeakEvent event = createWaterLeak();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("1");
		LinkedList<Event> events = agent.getEventList();
		assertEquals(1, events.size());
		agent = sim.getAgentByID("10");
		events = agent.getEventList();
		assertEquals(0, events.size());
	}
	
	@Test
	public void testIntruderCreation(){
		createIntruder();
	}
	
	@Test
	public void insertIntruder(){
		IntruderEvent event = createIntruder();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("1");
		LinkedList<Event> events = agent.getEventList();
		assertEquals("0", events.get(0).getEventID());
	}
	
	@Test
	public void testMoveRobotCreation(){
		createMoveRobot();
	}
	
	@Test
	public void insertMoveRobot(){
		MoveRobotEvent event = createMoveRobot();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("1");
		LinkedList<Event> events = agent.getEventList();
		assertEquals("0", events.get(0).getEventID());
	}
	
	@Test
	public void testUserWaterLeak(){
		createWaterLeakForUser();
	}
	
	@Test
	public void insertUserWaterLeakNoRobots(){
		WaterLeakEvent event = createWaterLeakForUser();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("0");
		LinkedList<Event> events = agent.getEventList();
		assertEquals(0, events.size());
	}
	
	@Test
	public void insertUserWaterLeak(){
		WaterLeakEvent event = createWaterLeakForUser();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("5");
		LinkedList<Event> events = agent.getEventList();
		assertEquals(1, events.size());
		agent = sim.getAgentByID("10");
		events = agent.getEventList();
		assertEquals(1, events.size());
		agent = sim.getAgentByID("1");
		events = agent.getEventList();
		assertEquals(1, events.size());
		agent = sim.getAgentByID("0");
		events = agent.getEventList();
		assertEquals(0, events.size());
	}
	
	@Test
	public void testUserMessage(){
		createUserMessage();
	}
	
	@Test
	public void agentAddedButRequestingByBadID(){
		UserMessageEvent event = createUserMessage();
		sim.incomingEvent(event);
		Agent agent = sim.getAgentByID("5");
		LinkedList<Event> events = agent.getEventList();
		assertEquals(0, events.size());
	}

	@Test
	public void agentNotAddedButRequestedByID(){
		assertNull(sim.getAgentByID("4"));
	}
	
	@Test
	public void agentAddingGoodID(){
		assertTrue(sim.addAgent(new Robot("112", "0")));
		assertNotNull(sim.getAgentByID("112"));
		assertEquals("112", sim.getAgentByID("112").getID());
	}
}
