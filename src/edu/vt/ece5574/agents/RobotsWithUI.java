import sim.engine.*;
import sim.display.*;

public class RobotsWithUI extends GUIState
{
	public static void main(String[] args)
	{
		RobotsWithUI vid = new RobotsWithUI();
		Console c = new Console(vid);
		c.setVisible(true);
	}
	
	public RobotsWithUI()
	{
		super(new Robots(System.currentTimeMillis()));
	}
	
	public RobotsWithUI(SimState state)
	{
		super(state);
	}
	
	public static String getName() 
	{
		return "Robots Simulation";
	}
}