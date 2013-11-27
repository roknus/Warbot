package edu.turtlekit2.warbot.roknus.FSMExplorer;

import edu.turtlekit2.warbot.roknus.BrainExplorer;

public class StateAttack extends State
{
	public StateAttack(BrainExplorer brain) 
	{
		super("Attack", brain);
	}

	@Override
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
