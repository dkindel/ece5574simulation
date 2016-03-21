package edu.vt.ece5574.dummyclasses;

import edu.vt.ece5574.agents.AbstractAgent;
import sim.engine.SimState;

public class DummyBuilding extends AbstractAgent{
	private static final long serialVersionUID = 1;
	private int numRooms;
	private int numFloors;
	
	public DummyBuilding(){
		
	}
	


	@Override
	public void step(SimState arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * @return the numFloors
	 */
	public int getNumFloors() {
		return numFloors;
	}

	/**
	 * @param numFloors the numFloors to set
	 */
	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
	}



	/**
	 * @return the numRooms
	 */
	public int getNumRooms() {
		return numRooms;
	}



	/**
	 * @param numRooms the numRooms to set
	 */
	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}


}
