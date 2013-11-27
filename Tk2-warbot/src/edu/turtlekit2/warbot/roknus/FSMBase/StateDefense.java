package edu.turtlekit2.warbot.roknus.FSMBase;

import edu.turtlekit2.warbot.roknus.BrainBase;

public class StateDefense extends State
{
	public StateDefense(BrainBase brain) 
	{
		super("Defense", brain);
	}

	@Override
	public String action()
	{	
		messageHandler();
		
		getBrain().broadcastMessage("WarExplorer", "WarBasePosition", null);
		getBrain().broadcastMessage("WarRocketLauncher", "WarBasePosition", null);
		

		if(!getBrain().emptyBag())
		{
			return "eat";
		}	
		
		if(getBrain().getEnergy() > 3000)
		{
			getBrain().setNextAgentCreate("Explorer");
			return "create";
		}
		
		return "action";
	}
}
