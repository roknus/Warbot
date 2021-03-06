package edu.turtlekit2.warbot.roknus.FSMRocketLauncher;

import java.util.List;

import edu.turtlekit2.warbot.percepts.Percept;
import edu.turtlekit2.warbot.roknus.BrainRocketLauncher;

public class StateDefense extends State 
{
	public StateDefense(BrainRocketLauncher brain) 
	{
		super("Defense", brain);
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

	protected void perceptHandler()
	{		
		List<Percept> listeP = getBrain().getPercepts();
		
		for(Percept p : listeP)
		{
			if(p.getType().equals("WarBase") && !p.getTeam().equals(getBrain().getTeam()))
			{
				double angleGamma = (p.getAngle() - getBrain().getMyBaseAngle());
				if(angleGamma < 0)
					angleGamma += 360;
				double d = getBrain().getDistanceAlKashi(p.getDistance(), getBrain().getMyBaseDistance(), angleGamma);
				double a = getBrain().getAngleAlKashi(d, getBrain().getMyBaseDistance(), p.getDistance());
				
				String[] content = new String[2];
				content[0] = Double.toString(((angleGamma > 180) || ((angleGamma < 0) && (angleGamma > -180))) ? a : -a);
				content[1] = Double.toString(d);
				
				getBrain().broadcastMessage("WarBase", "EnemyWarBasePosition", content);
				
				getBrain().setState(getBrain().getStates().get("Attack"));
			}
		}
	}
}
