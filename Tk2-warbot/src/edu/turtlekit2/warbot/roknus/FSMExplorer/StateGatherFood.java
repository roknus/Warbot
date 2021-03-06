package edu.turtlekit2.warbot.roknus.FSMExplorer;

import java.util.List;

import edu.turtlekit2.warbot.percepts.Percept;
import edu.turtlekit2.warbot.roknus.BrainExplorer;
import edu.turtlekit2.warbot.waritems.WarFood;;

public class StateGatherFood extends State 
{
	private Percept bestFood;
	
	public StateGatherFood(BrainExplorer brain)
	{
		super("GatherFood", brain);
		bestFood = null;
	}

	@Override
	public String action()
	{
		setBestFood(null);// Pour ne pas qu'il soit comme un con si on lui pique la bouffe sous le nez
		
		messageHandler();
		perceptHandler();
		
		if(getBrain().fullBag())
		{
			getBrain().setState(getBrain().getStates().get("ReturnBase"));
			getBrain().setHeading(getBrain().getMyBaseAngle());
			return "move";
		}
		
		if(getBestFood() != null)
		{
			if(getBestFood().getDistance() < WarFood.MAX_DISTANCE_TAKE)
			{
				return "take";
			}
			getBrain().setHeading(getBestFood().getAngle());
		}
		else
		{
			while(getBrain().isBlocked())
			{                       
				getBrain().setRandomHeading();
			}
		}

		return "move";
	}
	
	@Override
	protected void perceptHandler()
	{
		List<Percept> listeP = getBrain().getPercepts();
		
		if(listeP.size() > 0)
		{
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
				}
				if(p.getType().equals("WarFood"))
				{
					if(getBestFood() == null || p.getDistance() < getBestFood().getDistance())
					{
						setBestFood(p);
					}
				}
			}
		}
	}

	public Percept getBestFood() {
		return bestFood;
	}

	public void setBestFood(Percept bestFood) {
		this.bestFood = bestFood;
	}
}
