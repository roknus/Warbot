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
    ArrayList<dataExplorerRocket> explorer;
    ArrayList<dataExplorerRocket> rocket;

	public BrainBase()
	{
		explorer = new ArrayList<dataExplorerRocket>();
		rocket = new ArrayList<dataExplorerRocket>();
	}

	@Override
	public String action() {
		
		if(!emptyBag())
		{
			return "eat";
		}		

		List<WarMessage> liste = getMessage();
		
		for(WarMessage m : liste)
		{
			if(m.getMessage().equals("MyPosition"))
			{
				boolean test = true;
				if(m.getType().equals("WarExplorer"))
				{
					for(dataExplorerRocket d : explorer)
					{
						if(m.getSender() == d.getId())
						{
							d.setAngle(m.getAngle()-this.getHeading());
							d.setDistance(m.getDistance());
							test = false;
						}
					}
					if(test)
					{
						explorer.add(new dataExplorerRocket(m.getSender(),m.getAngle(),m.getDistance()));
					}
				}
				else if(m.getType().equals("WarRocketLauncher"))
				{
					for(dataExplorerRocket d : rocket)
					{
						if(m.getSender() == d.getId())
						{
							double a = m.getAngle();
							if(a < 0)
							{
								a+=360;
							}
							d.setAngle(a-getEnemyBaseAngle());
							d.setDistance(m.getDistance());
							test = false;
						}
					}
					if(test)
					{
						rocket.add(new dataExplorerRocket(m.getSender(),m.getAngle(),m.getDistance()));
					}
				}
			}
			else if(m.getMessage().equals("EnemyBase"))
			{
				this.setEnemyBaseAngle((m.getAngle() + Double.parseDouble(m.getContent()[0])));
				this.setEnemyBaseDistance((Double.parseDouble(m.getContent()[1])));
//				this.setHeading(getEnemyBaseAngle());
				
		        for(dataExplorerRocket r : rocket)
		        {
		                String content[] = new String[1];
		                double D = this.getDistance(getEnemyBaseDistance(), r.getDistance(), r.getAngle());
		                double A = this.getAngle(r.getDistance(), D, getEnemyBaseDistance());
		                
		                double B = r.getAngle();
		                if(B > 0)
		                {
		                	content[0] = Double.toString(A);
		                }
		                else
		                {
		                	content[0] = Double.toString(-A);
		                }
		                
		                sendMessage(r.getId(),"EnemyBase",content);
		        }				
			}					
		}
		broadcastMessage("WarExplorer", "WarBasePosition", null);
		broadcastMessage("WarRocketLauncher", "WarBasePosition", null);
		
		if(getEnergy() > 12000)
		{
			setNextAgentCreate("Explorer");
			return "create";
		}
		
		return "action";
	}
}
