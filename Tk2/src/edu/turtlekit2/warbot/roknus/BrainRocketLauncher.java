package edu.turtlekit2.warbot.roknus;

import java.util.List;

import edu.turtlekit2.warbot.WarBrain;
import edu.turtlekit2.warbot.message.WarMessage;
import edu.turtlekit2.warbot.percepts.Percept;

public class BrainRocketLauncher extends BrainUnit
{
	private boolean warbase;
	
	public BrainRocketLauncher()
	{
		setWarbase(false);
	}
	
	@Override
	public String action() 
	{
		List<WarMessage> listeM = getMessage();
	
		for(WarMessage m : listeM)
		{
			if(m.getMessage().equals("WarBasePosition") && !this.getWarbase())
			{
				//this.setHeading(m.getAngle());
			}
			if(m.getMessage().equals("EnemyBase"))
			{
				this.setWarbase(true);
				this.setHeading(m.getAngle()+Double.parseDouble(m.getContent()[0]));
			}
		}
		this.broadcastMessage("WarBase", "MyPosition", null);
		
		while(this.isBlocked())
		{
			this.setRandomHeading();
		}

		return "move";
		
//		if(!isReloaded()){
//			if(!isReloading()){
//				return "reload";
//			}
//		}
//		
//		List<Percept> listeP = getPercepts();
//		List<WarMessage> listeM = getMessage();
//		
//		
//		Percept bestPercept = null;
//		
//		if(listeM.size() == 0){
//			for(Percept p : listeP){
//				if(p.getType().equals("WarBase") && !p.getTeam().equals(getTeam())){
//					bestPercept = p;
//				}
//			}
//			
//			if(bestPercept != null){
//				broadcastMessage("WarRocketLauncher", "base", null);
//				setAngleTurret(bestPercept.getAngle());
//				return "fire";
//			}else{
//				while(isBlocked()){
//					setRandomHeading();
//				}
//				return "move";
//			}
//		}else{
//			for(Percept p : listeP){
//				if(p.getType().equals("WarBase") && !p.getTeam().equals(getTeam())){
//					bestPercept = p;
//				}
//			}
//			
//			if(bestPercept != null){
//				broadcastMessage("WarRocketLauncher", "base", null);
//				setAngleTurret(bestPercept.getAngle());
//				return "fire";
//			}else if(listeM.size()>0){
//				WarMessage tmp = listeM.get(0);
//				reply(tmp, "j'arrive", null);
//				setHeading(tmp.getAngle());
//			}
//		}
//		
//		return "move";
	}

	public boolean getWarbase() {
		return warbase;
	}

	public void setWarbase(boolean warbase) {
		this.warbase = warbase;
	}
}
