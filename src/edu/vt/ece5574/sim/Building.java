package edu.vt.ece5574.sim;

import edu.vt.ece5574.agents.AbstractAgent;
import sim.engine.SimState;



public class Building extends AbstractAgent{
	
	public enum Obstacle {
	    obstacle, emergency 
	}
	
	
	private static final long serialVersionUID = 1;
	private int numRooms;
	private int numFloors;
	
    private int width;
    private int height;

    final int minWidth = 3;
    final int maxWidth = 100;
    final int minHeight = 3;
    final int maxHeight = 100;
	
    private Matrix matrix;
    
    
	public Building(int width, int height, int floor, int room){
		super(-2);
		
		this.numRooms = room;
		this.numFloors = floor;
		
		 try {
	            if (  width < minWidth  ||  width > maxWidth  ) {
	                width = minWidth;
	            }

	            if (  height < minHeight  ||  height > maxHeight  ) {
	                height = minHeight;
	            }
	        }
	        catch ( Exception error ) {
	          
	        }
		
		 this.matrix = new Matrix(width,height);
		 
		
	}
	
    public int getFloorWidth() {
        return width;
    }

    public int getFloorHeight() {
        return height;
    }
    
    

	@Override
	public void step(SimState arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertObstacle(int x, int y, int width, int height, Obstacle type){
    	int code = 0;
    	
    	  switch (type) {
          case obstacle:  code = 1;
                   break;
          case emergency:  code = 2;
                   break;
          default: code = 0;
                   break;
      }
    	
    	 for (int i = x;i <= width;i++){
    		 for (int j=y; j <= height; j++){
    			 this.matrix.data[i][j] = code;
    		 }
    	 }
    	  
    }
	
	public boolean checkStep(int x, int y){
		
		if (  x < this.width  ||  width > this.height  ) {
            return false;
        }
		else if (  y < this.height  ||  height > this.height  ) {
            return false;
        }
		else if(matrix.data[x][y] != 0){
			return false;
		}
		else 
		return true; 
	}

}
