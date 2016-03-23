package edu.vt.ece5574.agents;

import java.awt.Color;
import java.util.LinkedList;

import edu.vt.ece5574.events.Event;
import sim.engine.Steppable;
import sim.portrayal.simple.OvalPortrayal2D;


/**
 * Abstract class for agents to inherit.  These will have base 
 * implementations that are generic across all agents
 * 
 * @author David Kindel
 */
public abstract class Agent extends OvalPortrayal2D implements Steppable {

	private static final long serialVersionUID = 1;
	
	protected String id = "abcd";
	protected String buildingID = "0";
	
	protected LinkedList<Event> events;

	private void init(String id_, String buildingID_){
		id = id_;
		buildingID = buildingID_;
		events = new LinkedList<Event>();
	}
	
	public Agent(String id_, String buildingID_){
		super();
		init(id_, buildingID_);
	}
	
	public Agent(double scale, String id_, String buildingID_){
		super(scale);
		init(id_, buildingID_);
	}
	
	public Agent(double scale, boolean filled, String id_, String buildingID_){
		super(scale, filled);
		init(id_, buildingID_);
	}
	
	public Agent(Color c, String id_, String buildingID_){
		super(c);
		init(id_, buildingID_);
	}
	
	public Agent(Color c, boolean filled, String id_, String buildingID_){
		super(c, filled);
		init(id_, buildingID_);
	}

	public Agent(Color c, double scale, String id_, String buildingID_){
		super(c, scale);
		init(id_, buildingID_);
	}
	
	public Agent(Color c, double scale, boolean filled, String id_, String buildingID_){
		super(c, scale, filled);
		init(id_, buildingID_);
	}

	public void addEvent(Event event) {
		events.add(event);
	}
	
	public LinkedList<Event> getEventList(){
		return events;
	}
	
	public String getID(){
		return id;
	}
	
	
}
