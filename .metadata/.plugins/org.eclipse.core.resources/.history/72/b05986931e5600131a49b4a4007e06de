package edu.turtlekit2.warbot.roknus.FSMRocketLauncher;

import edu.turtlekit2.warbot.roknus.BrainRocketLauncher;

public class StateAttack extends State 
{
	public StateAttack(BrainRocketLauncher brain)
	{
		super("Attack", brain);
	}
	
	public String action()
	{
		messageHandler();
		
		getBrain().broadcastMessage("WarBase", "MyPosition", null);
		
		while(getBrain().isBlocked())
		{
			getBrain().setRandomHeading();
		}

		return "move";
	}
}
