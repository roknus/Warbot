package edu.turtlekit2.warbot.roknus;

import java.util.ArrayList;
import java.util.List;

import edu.turtlekit2.warbot.WarBrain;
import edu.turtlekit2.warbot.message.WarMessage;

public class BrainBase extends WarBrain{
	
    ArrayList<dataExplorerRocket> explorer;
    ArrayList<dataExplorerRocket> rocket;
    private double distanceBaseEnemy;
	private double angleBaseEnemy;

	public BrainBase()
	{
		explorer = new ArrayList<dataExplorerRocket>();
		rocket = new ArrayList<dataExplorerRocket>();
		this.angleBaseEnemy = 0;
		this.distanceBaseEnemy = 0;
	}

	@Override
	public String action() {
		
		if(!emptyBag()){
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
							d.setAngle(a-this.getHeading());
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
				this.setAngleBaseEnemy(m.getAngle() + Double.parseDouble(m.getContent()[0]));
				this.setDistanceBaseEnemy(Double.parseDouble(m.getContent()[1]));
				this.setHeading(this.getAngleBaseEnemy());
				
		        for(dataExplorerRocket r : rocket)
		        {
		                String content[] = new String[1];
		                double D = this.getDistance(this.getDistanceBaseEnemy(), r.getDistance(), r.getAngle());
		                double A = this.getAngle(r.getDistance(), D, this.getDistanceBaseEnemy());
		                
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
	
	public double getDistance(double A,double B, double gamma)
	{// retourne la distance oppos� a gamma
//		System.out.println("GetDistance :");
//		System.out.println(A);
//		System.out.println(B);
//		System.out.println(gamma);
		return Math.sqrt( A*A + B*B - (2 * B * A * Math.cos(Math.toRadians(gamma))));
	}
	
	public double getAngle(double A, double B, double C)
	{// retourne l'angle entre A et B
		if(A > 1 && B > 1)
		{
//			System.out.println("GetAngle :");
//			System.out.println(A);
//			System.out.println(B);
//			System.out.println(C);
			double a = (A*A + B*B - C*C)/(2*A*B);
			if(a > -1 && a < 1)
			{
				return Math.toDegrees(Math.acos(a));
			}
			else return 180;
		}
		else
		{
			return 180;
		}
	}

	public double getAngleBaseEnemy() {
		return angleBaseEnemy;
	}

	public void setAngleBaseEnemy(double baseEnemy) {
		this.angleBaseEnemy = baseEnemy;
	}	
	public double getDistanceBaseEnemy() {
		return distanceBaseEnemy;
	}

	public void setDistanceBaseEnemy(double distanceBaseEnemy) {
		this.distanceBaseEnemy = distanceBaseEnemy;
	}
}
