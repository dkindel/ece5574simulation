package edu.vt.ece5574.agents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;


import edu.vt.ece5574.events.Event;
import edu.vt.ece5574.events.FireEvent;
import edu.vt.ece5574.events.IntruderEvent;
import edu.vt.ece5574.events.WaterLeakEvent;
import edu.vt.ece5574.sim.Simulation;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.MutableDouble2D;

public class Robots extends OvalPortrayal2D implements Steppable {

	private static final long serialVersionUID = 1;
	private static  int robotID;
	LinkedList<Event> currEvents;
	
	public MutableDouble2D loc, velocity;
    public MutableDouble2D newLoc = new MutableDouble2D();
  //  public MutableDouble2D sumVector = new MutableDouble2D(0,0);

    public double speed, radius =2;
    
    public double cap;
    
    public double mass;
        
    public double getX() { return loc.x; }
    public void setX( double newX ) { loc.x = newX; }
    
    public double getY() { return loc.y; }
    public void setY( double newY ) { loc.y = newY; }
    
    public double getVelocityX() { return velocity.x; }
    public void setVelocityX( double newX ) { velocity.x = newX; }
    
    public double getVelocityY() { return velocity.y; }
    public void setVelocityY( double newY ) { velocity.y = newY; }
 
    public double getSpeed() { return speed; }
    public void setSpeed( double newSpeed ) { speed = newSpeed; }   
    
    public double getRadius() { return radius; }
    public void setRadius( double newRadius ) 
        {
        radius = newRadius;
        scale = 2 * radius;  
        } 
    
    
    
	
	public Robots( double newX, double newY,  Color c , int rID)
    {
    super(c, 2 * 2);  // scale is twice the radius
    
    robotID = rID;
    
    loc = new MutableDouble2D(newX, newY);
    velocity = new MutableDouble2D(0, 0);
    
    radius = 2;
    
    cap = 1.0;
    
    speed = 0.1;
    }
	


	public void dealWithEvents(){
		while(currEvents.size()!=0){
			Event evnt = currEvents.removeFirst();
			if(evnt instanceof FireEvent){
				//need building impl to reach the event
			}
			else if(evnt instanceof IntruderEvent){
				//need building impl to reach the event
			}
			else if(evnt instanceof WaterLeakEvent){
				//need building impl to reach the event
			}
			
		}
	}
	
	public void randomMovement(){
		//Logic to move around
	}
	
	@Override
	public void step(SimState state) {
		//Check for events
		Simulation simState = (Simulation)state;
		simState.getEventsForRobotID(robotID);
		currEvents.addAll(simState.getEventsForRobotID(robotID));
		if(currEvents.isEmpty()){
			//if no event, move randomly to collect sensor data
			randomMovement();
		}
		else{
			//in case of events react
			dealWithEvents();
		}
		
		
		
		
		
	}

}
