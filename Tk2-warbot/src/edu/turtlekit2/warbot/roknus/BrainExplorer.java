package edu.turtlekit2.warbot.roknus;

import java.util.List;
import edu.turtlekit2.warbot.message.WarMessage;
import edu.turtlekit2.warbot.percepts.Percept;
import edu.turtlekit2.warbot.waritems.WarFood;

public class BrainExplorer extends BrainUnit
{
	
	public BrainExplorer()
	{
		super();
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
				if(m.getSenderType().equals("WarBase"))
				{
					if(m.getMessage().equals("WarBasePosition"))
					{
						setMyBaseAngle(m.getAngle());
						setMyBaseDistance(m.getDistance());
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
					double anglePercept = p.getAngle();
					if(anglePercept < 0)
						anglePercept += 360;
					
					double angleGamma = (anglePercept - getMyBaseAngle());
					double d = this.getDistanceAlKashi(p.getDistance(), getMyBaseDistance(), angleGamma);
					double a = this.getAngleAlKashi(d, getMyBaseDistance(), p.getDistance());
					
					String[] content = new String[2];
					content[0] = Double.toString(((angleGamma > 180) || ((angleGamma < 0) && (angleGamma > -180))) ? a : -a);
					content[1] = Double.toString(d);
					
					System.out.println(angleGamma);
					
					broadcastMessage("WarBase", "EnemyWarBasePosition", content);
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
}
