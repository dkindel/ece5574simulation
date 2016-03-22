package edu.vt.ece5574.sim;
import edu.vt.ece5574.sim.callPushAPI;
import py4j.GatewayServer;


public class startServer {

	public startServer(){
		GatewayServer gatewayServer = new GatewayServer(new APICaller()); 
	    gatewayServer.start();
		
	}
	
}
