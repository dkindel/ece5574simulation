package edu.vt.ece5574.agents;

import java.awt.Color;

import sim.engine.Steppable;
import sim.portrayal.simple.OvalPortrayal2D;


/**
 * Abstract class for agents to inherit.  These will have base 
 * implementations that are generic across all agents
 * 
 * @author David Kindel
 */
public abstract class AbstractAgent extends OvalPortrayal2D implements Steppable {

	private static final long serialVersionUID = 1;
	
	protected int id = -1;

	public AbstractAgent(int id_){
		id = id_;
	}
	
	public AbstractAgent(double scale, int id_){
		super(scale);
		id = id_;
	}
	
	public AbstractAgent(double scale, boolean filled, int id_){
		super(scale, filled);
		id = id_;
	}
	
	public AbstractAgent(Color c, int id_){
		super(c);
		id = id_;
	}
	
	public AbstractAgent(Color c, boolean filled, int id_){
		super(c, filled);
		id = id_;
	}

	public AbstractAgent(Color c, double scale, int id_){
		super(c, scale);
		id = id_;
	}
	
	public AbstractAgent(Color c, double scale, boolean filled, int id_){
		super(c, scale, filled);
		id = id_;
	}
	
	
}
