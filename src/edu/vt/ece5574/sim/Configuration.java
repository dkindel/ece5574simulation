package edu.vt.ece5574.sim;

//Maybe we shouldn't do json or XML?  There's a java properties file format
//that might be of interest: 
//http://cs.gmu.edu/~eclab/projects/mason/extensions/webtutorial1/#param-file
//Honestly, that seems like a smart decision but it's up to the developer
//
//You should instantiate agents here on loading the config and provided a way 
//to get them back to main through function calls of some sort
//
//Should seed, time and other cmd line args be placed in the config or fed in 
//through the config file?  The config file will take some circumventing of 
//MASON's built in functionality but is possible.  Just annoying. 

public class Configuration {
	
	/**
	 * 
	 */
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
	
	public int getNumBuildings(){
		return 1;
	}
}
