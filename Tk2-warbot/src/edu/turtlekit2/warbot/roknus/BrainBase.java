package edu.turtlekit2.warbot.roknus;

import java.util.ArrayList;
import java.util.Hashtable;

import edu.turtlekit2.warbot.roknus.FSMBase.*;

/**
 * Class of the Brain Base, it implement specifics methods and attributes of the base
 * @author Lucia Florent, Lagniez Jean-Marc
 * @version 0.1
 */
public class BrainBase extends BrainAgent
{
	private Hashtable<String,State> states;
	State state;
    ArrayList<UnitData> warExplorerList;
    ArrayList<UnitData> warRocketLauncherList;
    
	public BrainBase()
	{
		super();
		state = new StateDefense(this);
		warExplorerList = new ArrayList<UnitData>();
		warRocketLauncherList = new ArrayList<UnitData>();
	}

	@Override
	public String action()
	{		
		return state.action();
	}
	
	@Override
	protected void initializeStatesHash()
	{
		// TODO Auto-generated method stub	
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ArrayList<UnitData> getWarExplorerList() {
		return warExplorerList;
	}

	public void setWarExplorerList(ArrayList<UnitData> warExplorerList) {
		this.warExplorerList = warExplorerList;
	}

	public ArrayList<UnitData> getWarRocketLauncherList() {
		return warRocketLauncherList;
	}

	public void setWarRocketLauncherList(ArrayList<UnitData> warRocketLauncherList) {
		this.warRocketLauncherList = warRocketLauncherList;
	}

	public Hashtable<String, State> getStates() {
		return states;
	}

	public void setStates(Hashtable<String, State> states) {
		this.states = states;
	}
}
