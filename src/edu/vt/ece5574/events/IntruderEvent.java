package edu.vt.ece5574.events;


public class IntruderEvent extends Event {

	public IntruderEvent(){
		
	}

	public boolean init(String details){
		if(super.setBaseDetails(details)){
			return true;
		}
		return false;
	}
}
