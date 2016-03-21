package edu.vt.ece5574.events;

public class WaterLeakEvent extends Event{


	public WaterLeakEvent(){
		
	}
	

	public boolean init(String details){
		if(super.setBaseDetails(details)){
			return true;
		}
		return false;
	}
}
