import sim.engine.*;
import sim.display.*;

public class PeopleWithUI extends GUIState
{
	public static void main(String[] args)
	{
		PeopleWithUI vid = new PeopleWithUI();
		Console c = new Console(vid);
		c.setVisible(true);
	}
	
	public PeopleWithUI()
	{
		super(new People(System.currentTimeMillis()));
	}
	
	public PeopleWithUI(SimState state)
	{
		super(state);
	}
	
	public static String getName() 
	{
		return "People Simulation";
	}
}