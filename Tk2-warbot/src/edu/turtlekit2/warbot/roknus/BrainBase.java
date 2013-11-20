package edu.turtlekit2.warbot.roknus;

import java.util.ArrayList;
import java.util.List;
import edu.turtlekit2.warbot.message.WarMessage;

/**
 * Class of the Brain Base, it implement specifics methods and attributes of the base
 * @author Lucia Florent, Lagniez Jean-Marc
 * @version 0.1
 */
public class BrainBase extends BrainAgent
{
    ArrayList<UnitData> warExplorerList;
    ArrayList<UnitData> warRocketLauncherList;
    
	public BrainBase()
	{
		super();
		warExplorerList = new ArrayList<UnitData>();
		warRocketLauncherList = new ArrayList<UnitData>();
	}

	@Override
	public String action()
	{		
		if(!emptyBag())
		{
			return "eat";
		}		
		
		messageHandler();
		
		broadcastMessage("WarExplorer", "WarBasePosition", null);
		broadcastMessage("WarRocketLauncher", "WarBasePosition", null);
		
		if(getEnergy() > 12000)
		{
			setNextAgentCreate("Explorer");
			return "create";
		}
		
		return "action";
	}
	
	/**
	 * Handle the new messages received since the previous tick
	 */
	private void messageHandler()
	{
		List<WarMessage> liste = getMessage();
		
		for(WarMessage m : liste)
		{
			if(m.getMessage().equals("MyPosition"))
			{
				boolean test = true;
				if(m.getSenderType().equals("WarExplorer"))
				{
					for(UnitData d : warExplorerList)
					{
						if(m.getSender() == d.getId())
						{
							d.setAngle(m.getAngle());
							d.setDistance(m.getDistance());
							test = false;
						}
					}
					if(test)
					{
						warExplorerList.add(new UnitData(m.getSender(),m.getAngle(),m.getDistance()));
					}
				}
				else if(m.getSenderType().equals("WarRocketLauncher"))
				{
					for(UnitData d : warRocketLauncherList)
					{
						if(m.getSender() == d.getId())
						{
							d.setAngle(m.getAngle());
							d.setDistance(m.getDistance());
							test = false;
						}
					}
					if(test)
					{
						warRocketLauncherList.add(new UnitData(m.getSender(),m.getAngle(),m.getDistance()));
					}
				}
			}
			else if(m.getMessage().equals("EnemyWarBasePosition"))
			{
				setEnemyBaseAngle((m.getAngle() + Double.parseDouble(m.getContent()[0])));
				setEnemyBaseDistance((Double.parseDouble(m.getContent()[1])));
				
		        for(UnitData r : warRocketLauncherList)
		        {
		        		r.setAngleEnemyBase(r.getAngle() - getEnemyBaseAngle());
		                double d = getDistanceAlKashi(getEnemyBaseDistance(), r.getDistance(), r.getAngleEnemyBase());
		                double a = getAngleAlKashi(r.getDistance(), d, getEnemyBaseDistance());

		                String content[] = new String[1];
		                content[0] = Double.toString((r.getAngleEnemyBase() > 0) ? a : -a);    
		                
		                sendMessage(r.getId(),"EnemyWarBasePosition",content);
		        }				
			}					
		}
	}
}
