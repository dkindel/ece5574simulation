package edu.vt.ece5574.events;

public class FireEvent extends Event {

	public FireEvent() {
		
	}

	public boolean init(String details){
		if(super.setBaseDetails(details)){
			return true;
		}
		return false;
	}

}
