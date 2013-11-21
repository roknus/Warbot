package edu.turtlekit2.warbot.roknus;

public class UnitData 
{
	private int id;
	
    private double angle;
    private double distance;
    
    private double angleEnemyBase;
    
    public UnitData()
    {
    	setId(0);
    	setAngle(0);
    	setDistance(0);
    }
    
    public UnitData(int id, double angle, double distance)
    {
    	setId(id);
    	setAngle(angle);
    	setDistance(distance);
    }

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public double getAngle() 
	{
		return angle;
	}

	public void setAngle(double angle) 
	{
		this.angle = (angle < 0) ? angle + 360 : angle;
	}

	public double getAngleEnemyBase() 
	{
		return angleEnemyBase;
	}

	public void setAngleEnemyBase(double angleEnemyBase) 
	{
		this.angleEnemyBase = angleEnemyBase;
	}   

	public double getDistance() 
	{
		return distance;
	}

	public void setDistance(double distance) 
	{
		this.distance = distance;
	}
}
