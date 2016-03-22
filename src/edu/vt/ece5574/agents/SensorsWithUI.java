import sim.engine.*;
import sim.display.*;

public class SensorsWithUI extends GUIState
{
	public static void main(String[] args)
	{
		SensorsWithUI vid = new SensorsWithUI();
		Console c = new Console(vid);
		c.setVisible(true);
	}
	
	public SensorsWithUI()
	{
		super(new Sensors(System.currentTimeMillis()));
	}
	
	public SensorsWithUI(SimState state)
	{
		super(state);
	}
	
	public static String getName() 
	{
		return "Sensors Simulation";
	}
}