package edu.turtlekit2.warbot.roknus;

public abstract class BrainUnit extends BrainAgent 
{
	protected double myBaseAngle;
	protected double myBaseDistance;
	
	public BrainUnit()
	{
		super();
		setMyBaseAngle(0);
		setMyBaseDistance(0);
	}
	
	public double getMyBaseAngle()
	{
		return myBaseAngle;
	}
	public void setMyBaseAngle(double myBaseAngle) 
	{
		this.myBaseAngle = (myBaseAngle < 0) ? myBaseAngle += 360 : myBaseAngle;
	}
	public double getMyBaseDistance() 
	{
		return myBaseDistance;
	}
	public void setMyBaseDistance(double myBaseDistance) 
	{
		this.myBaseDistance = myBaseDistance;
	}
	
	
}
