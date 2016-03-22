package edu.vt.ece5574.agents;

import sim.engine.Steppable;

public abstract class AbstractAgent implements Steppable {

	private static final long serialVersionUID = 1;
	
	protected int id = -1;

	public AbstractAgent(int id_){
		id = id_;
	}
	
}
