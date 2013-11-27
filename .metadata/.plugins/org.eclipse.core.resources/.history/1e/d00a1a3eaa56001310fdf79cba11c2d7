package edu.turtlekit2.warbot.roknus.FSMBase;

import edu.turtlekit2.warbot.roknus.BrainBase;

public class StateAttack extends State 
{
	public StateAttack(BrainBase brain) 
	{
		super("Attack", brain);
	}

	public String action()
	{
		if(!getBrain().emptyBag())
		{
			return "eat";
		}		
		
		messageHandler();
		
		getBrain().broadcastMessage("WarExplorer", "WarBasePosition", null);
		getBrain().broadcastMessage("WarRocketLauncher", "WarBasePosition", null);
		
		if(getBrain().getEnergy() > 12000)
		{
			getBrain().setNextAgentCreate("Explorer");
			return "create";
		}
		
		return "action";
	}
}
