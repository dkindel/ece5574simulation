package edu.vt.ece5574.sim;

public class Configuration {
	
	public Configuration(){
		
	}
	
	public Configuration(String filename){
		if(!load(filename)){
			System.err.println("Failed to load configuration file named " + filename);
			System.exit(-1);
		}
	}
	
	public boolean load(String filename){
		return false;
	}
}
