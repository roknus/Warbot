package edu.turtlekit2.warbot.roknus.FSMExplorer;

import java.util.List;

import edu.turtlekit2.warbot.message.WarMessage;
import edu.turtlekit2.warbot.percepts.Percept;
import edu.turtlekit2.warbot.roknus.BrainExplorer;

public abstract class State
{
	private String stateName;
	private BrainExplorer brain;
	
	public State()
	{
		setStateName("unknown");
		setBrain(null);
	}
	
	public State(String stateName, BrainExplorer brain)
	{
		setStateName(stateName);
		setBrain(brain);
	}
	
	public String action()
	{
		return "idle";
	}
	
	protected void messageHandler()
	{
		List<WarMessage> listeM = getBrain().getMessage();
		
		if(listeM.size() > 0)
		{
			for(WarMessage m : listeM)
			{
				if(m.getSenderType().equals("WarBase"))
				{
					if(m.getMessage().equals("WarBasePosition"))
					{
						getBrain().setMyBaseAngle(m.getAngle());
						getBrain().setMyBaseDistance(m.getDistance());
					}
				}
			}
		}
	}
	
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
			}
		}
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName) 
	{
		this.stateName = stateName;
	}

	public BrainExplorer getBrain() 
	{
		return brain;
	}

	public void setBrain(BrainExplorer brain)
	{
		this.brain = brain;
	}
	
}
