package edu.turtlekit2.warbot.roknus.FSMRocketLauncher;

import edu.turtlekit2.warbot.roknus.BrainAgent;
import edu.turtlekit2.warbot.roknus.BrainRocketLauncher;

public class StatePatrol extends State 
{
	public StatePatrol(BrainRocketLauncher brain) 
	{
		super("Patrol", brain);
	}
	
	@Override
	public String action()
	{
		messageHandler();
		perceptHandler();
		
		getBrain().broadcastMessage("WarBase", "MyPosition", null);
		
		while(getBrain().isBlocked())
		{
			getBrain().setHeading(getBrain().getHeading() + 180);
		}
		
		if(getBrain().getMyBaseDistance() > BrainAgent.MAX_DISTANCE_PATROL)
		{
			getBrain().setHeading(getBrain().getMyBaseAngle() + 90);
		}
		
		return "move";
	}

}
