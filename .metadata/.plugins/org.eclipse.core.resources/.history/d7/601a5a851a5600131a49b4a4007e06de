package edu.turtlekit2.warbot.roknus;

import java.util.List;

import edu.turtlekit2.warbot.message.WarMessage;
import edu.turtlekit2.warbot.percepts.Percept;
import edu.turtlekit2.warbot.roknus.FSMRocketLauncher.State;
import edu.turtlekit2.warbot.roknus.FSMRocketLauncher.StateDefense;

public class BrainRocketLauncher extends BrainUnit
{
	private boolean warbase;
	private State state;
	
	public BrainRocketLauncher()
	{
		state = new StateDefense(this);
		setWarbase(false);
	}
	
	@Override
	public String action() 
	{
		return state.action();
	}

	public boolean getWarbase() {
		return warbase;
	}

	public void setWarbase(boolean warbase) {
		this.warbase = warbase;
	}
}
