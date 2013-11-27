package edu.turtlekit2.warbot.roknus;

import java.util.Hashtable;

import edu.turtlekit2.warbot.roknus.FSMExplorer.*;

public class BrainExplorer extends BrainUnit
{
	private Hashtable<String,State> states;
	private State state;
	
	public BrainExplorer()
	{
		super();
		states = new Hashtable<String,State>();
		initializeStatesHash();
		state = states.get("GatherFood");
	}
	
	@Override
	protected void initializeStatesHash() 
	{
		states.put("Attack", new StateAttack(this));
		states.put("Defense", new StateDefense(this));
		states.put("GatherFood", new StateGatherFood(this));
		states.put("ReturnBase", new StateReturnBase(this));
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

	public Hashtable<String, State> getStates() {
		return states;
	}

	public void setStates(Hashtable<String, State> states) {
		this.states = states;
	}
}
