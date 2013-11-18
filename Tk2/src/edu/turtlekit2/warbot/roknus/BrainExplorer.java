package edu.turtlekit2.warbot.roknus;

import java.util.List;

import edu.turtlekit2.warbot.WarBrain;
import edu.turtlekit2.warbot.message.WarMessage;
import edu.turtlekit2.warbot.percepts.Percept;
import edu.turtlekit2.warbot.waritems.WarFood;

public class BrainExplorer extends WarBrain
{
	private int baseAngle;
	private int baseDistance;
	
	public BrainExplorer()
	{
		this.baseAngle = 0;		
			this.setBaseDistance(0);
	}
	
	@Override
	public String action()
	{
		List<Percept> liste = getPercepts();
		List<WarMessage> listeM = getMessage();
		
		if(liste.size() > 0)
		{
			for(WarMessage m : listeM)
			{
				if(m.getType().equals("WarBase"))
				{
					if(m.getMessage().equals("WarBasePosition"))
					{
						this.setBaseAngle(m.getAngle());
						this.setBaseDistance(m.getDistance());
					}
				}
			}
		}
		
		if(liste.size() > 0)
		{
			for(Percept p : liste)
			{
				if(p.getType().equals("WarBase") && !p.getTeam().equals(getTeam()))
				{
					int oldHeading = this.getHeading();
					this.setHeading(baseAngle);
					int A = p.getAngle();
					int B = p.getDistance();
					double C = this.getDistance(B, this.getBaseDistance() , A);
					double D = this.getAngle(C, this.getBaseDistance(), B);
					String[] content = new String[2];
					content[0] = Double.toString(D);
					content[1] = Double.toString(C);
					this.broadcastMessage("WarBase", "EnemyBase", content);
					
					this.setHeading(oldHeading);
				}
			}
		}
		
		this.broadcastMessage("WarBase", "MyPosition", null);
		
		while(this.isBlocked())
		{
			this.setRandomHeading();
		}
		return "move";
//		
//		if(liste.size()>0 && !fullBag()){
//			Percept bestFood = null;
//			
//			for(Percept p : liste){
//				if(p.getType().equals("WarFood")){
//					if(bestFood == null){
//						bestFood = p;
//					}else if(p.getDistance() < bestFood.getDistance()){
//						bestFood = p;
//					}
//				}
//			}
//			
//			if(bestFood != null && bestFood.getDistance() < WarFood.MAX_DISTANCE_TAKE){
//				return "take";
//			}else if(bestFood != null && bestFood.getType().equals("WarFood")){
//				setHeading(bestFood.getAngle());
//			}else{
//				while(isBlocked()){
//					setRandomHeading();
//				}
//			}
//		}else if(liste.size()>0 && fullBag()){
//			Percept bestFood = null;
//			
//			for(Percept p : liste){
//				if(p.getType().equals("WarBase") && p.getDistance() < WarFood.MAX_DISTANCE_TAKE){
//					if(bestFood == null){
//						bestFood = p;
//					}else if(p.getDistance() < bestFood.getDistance()){
//						bestFood = p;
//					}
//				}
//			}
//			
//			if(bestFood != null && bestFood.getDistance() < WarFood.MAX_DISTANCE_TAKE){
//				setAgentToGive(bestFood.getId());
//				return "give";
//			}else if(bestFood != null && bestFood.getType().equals("WarFood")){
//				setHeading(bestFood.getAngle());
//			}else{
//				while(isBlocked()){
//					setRandomHeading();
//				}
//			}
//		}else if(fullBag() && listeM.size()==0){
//			broadcastMessage("WarBase", "where", null);
//		}else if(fullBag() && listeM.size()>0){
//			setHeading(listeM.get(0).getAngle());
//		}else{
//			while(isBlocked()){
//				setRandomHeading();
//			}
//		}
//		return "move";
	}
	
	public double getDistance(double A,double B, double gamma)
	{// retourne la distance oppos� a gamma
		return Math.sqrt( A*A + B*B - (2 * B * A * Math.cos(Math.toRadians(gamma))));
	}
	
	public double getAngle(double A, double B, double C)
	{// retourne l'angle entre A et B
		return Math.toDegrees(Math.acos((A*A + B*B - C*C)/(2 * A * B)));
	}

	public int getBaseAngle() {
		return baseAngle;
	}

	public void setBaseAngle(int baseAngle) {
		this.baseAngle = baseAngle;
	}

	public int getBaseDistance() {
		return baseDistance;
	}

	public void setBaseDistance(int baseDistance) {
		this.baseDistance = baseDistance;
	}
}
