package edu.vt.ece5574.agents;

import java.util.LinkedList;
import java.util.Random;

import edu.vt.ece5574.events.Event;
import edu.vt.ece5574.events.FireEvent;
import edu.vt.ece5574.events.IntruderEvent;
import edu.vt.ece5574.events.WaterLeakEvent;
import edu.vt.ece5574.sim.Simulation;
import sim.engine.SimState;
import sim.engine.Steppable;
import edu.vt.ece5574.sim.Building;

//Class for sensors. - Author - Ameya Khandekar

public class Sensor implements Steppable {
	

	private static final long serialVersionUID = 1;
	private String sensorType;  // for now storing the sensor Type as a string.

	//private  LinkedList<Event> sensorEvents;
	private int sensorID ; 

	private int buildingID;
	private int roomID;

	private Random temperature;
	private Random waterPressure;

	private static final int DEFAULT_BUILDING = 0;
	private static final int DEFAULT_ROOM = 1;


	public Sensor(String typeOfSensor, int id , int roomID_, int buildingID_){
		sensorType = typeOfSensor;
		sensorID = id;
		roomID = roomID_;
		buildingID = buildingID_;
		temperature = new Random();
		waterPressure = new Random();
	}

	public Sensor(String typeOfSensor, int id){
		sensorType = typeOfSensor;
		sensorID = id;
		roomID = DEFAULT_ROOM;
		buildingID = DEFAULT_BUILDING;
		temperature = new Random();
		waterPressure = new Random();

	}


	public String getSensorType() { return sensorType; }
	public int getSensorID()   { return sensorID;   }





	@Override
	public void step(SimState state) {
		
		Simulation simState = (Simulation)state;

		// fireSensor adds fire event if temperature exceeds 1000 
		if(sensorType == "FIRE"){

			if(temperature.nextInt(10000) > 1000){
				FireEvent fireEvent = new FireEvent();
				simState.incomingEvent(fireEvent);
			}

		}

		// water leak sensor detects water leak if water pressure in the waterpipe goes below 100
		else if(sensorType == "WATERLEAK"){
			if(waterPressure.nextInt(10000) < 100){
				WaterLeakEvent leakEvent = new WaterLeakEvent();
				simState.incomingEvent(leakEvent);
			}

		}

		//will add case for intruder sensor later.


		//Check for events - for now not implemented.
		
/*		sensorEvents.addAll(simState.getEventsForRobotID(sensorID));

		if(!sensorEvents.isEmpty()){
			handleSensorEvents();
		}
*/
		
	}
}
