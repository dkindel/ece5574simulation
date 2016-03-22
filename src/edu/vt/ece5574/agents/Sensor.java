package edu.vt.ece5574.agents;

import java.util.Random;

import edu.vt.ece5574.events.FireEvent;
import edu.vt.ece5574.events.WaterLeakEvent;
import edu.vt.ece5574.sim.Simulation;
import sim.engine.SimState;
import sim.engine.Steppable;

//Class for sensors. - Author - Ameya Khandekar

public class Sensor implements Steppable {
	

	private static final long serialVersionUID = 1;
	private String sensorType;  // for now storing the sensor Type as a string.

	//private  LinkedList<Event> sensorEvents;
	private int sensorID ; 

	private Random temperature;
	private Random waterPressure;

	public Sensor(String typeOfSensor, int id , int roomID_, int buildingID_){
		sensorType = typeOfSensor;
		sensorID = id;
		temperature = new Random();
		waterPressure = new Random();
	}

	public Sensor(String typeOfSensor, int id){
		sensorType = typeOfSensor;
		sensorID = id;
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
