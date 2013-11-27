package edu.turtlekit2.warbot.roknus;

import java.util.List;

import edu.turtlekit2.warbot.message.WarMessage;
import edu.turtlekit2.warbot.percepts.Percept;
import edu.turtlekit2.warbot.roknus.FSMExplorer.*;
import edu.turtlekit2.warbot.waritems.WarFood;

public class BrainExplorer extends BrainUnit
{
	private State state;
	
	public BrainExplorer()
	{
		super();
		state = new StateDefense(this);
	}
	
	@Override
	public String action()
	{
		return state.action();
	}

	public State getState() {
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}
}
