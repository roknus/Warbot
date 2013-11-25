package edu.turtlekit2.warbot.roknus.FSMExplorer;

import edu.turtlekit2.warbot.roknus.BrainExplorer;

public class StateDefense extends State
{
	public StateDefense(BrainExplorer brain)
	{
		super("Defense", brain);
	}
	
	public String action()
	{
		messageHandler();
		perceptHandler();
		
		getBrain().broadcastMessage("WarBase", "MyPosition", null);
		
		while(getBrain().isBlocked())
		{                       
			getBrain().setRandomHeading();
		}
		return "move";
	}

}
