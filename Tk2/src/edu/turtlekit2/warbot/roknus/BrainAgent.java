package edu.turtlekit2.warbot.roknus;

import edu.turtlekit2.warbot.WarBrain;

/**
 * Abstract class for agent brain, implement general methods and attributes for angle calculation 
 * @author Lucia Florent, Lagniez Jean-Marc
 * @version 0.1
 */
public abstract class BrainAgent extends WarBrain 
{
	protected double enemyBaseAngle;
	protected double enemyBaseDistance;
	
	public BrainAgent()
	{
		setEnemyBaseAngle(0);
		setEnemyBaseDistance(0);
	}
	
	@Override
	public String action() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Al-Kashi theorem to find a side of a triangle when you have its opposite angle and the two others side
	 * @param A First side of the angle
	 * @param B Second side of the angle
	 * @param gamma Opposite angle of the side we are searching
	 * @return Return the opposite side of the angle
	 */
	public double getDistance(double A,double B, double gamma)
	{
		return Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2) - (2 * B * A * Math.cos(Math.toRadians(gamma))));
	}
	
	/**
	 * Al-Kashi theorem to find an angle of a triangle when you have the length of the 3 sides
	 * @param A First side of the angle
	 * @param B Second side of the angle
	 * @param C Opposite side of the angle
	 * @return Return the angle in degree between A and B
	 */
	public double getAngle(double A, double B, double C)
	{
		if(A > 0 && B > 0) // Division by 0
		{
			double a = (Math.pow(A, 2) + Math.pow(B, 2) - Math.pow(C, 2)) / (2 * A * B);
			if(a > -1 && a < 1) // Arccos take parameters between -1 and 1
			{
				return Math.toDegrees(Math.acos(a));
			}
		}
		return 180; // For particular case (2AB close to 0 and (a < -1 && a > 1)) the return is close to 180�
	}
	
	/**
	 * Return the angle be to oriented to the enemy base
	 * @return Return an angle in degree to be oriented to the enemy base
	 */
	public double getEnemyBaseAngle()
	{
		return enemyBaseAngle;
	}
	
	/**
	 * Set the angle to be oriented to the enemy base
	 * @param enemyBaseAngle an angle in degree
	 */
	public void setEnemyBaseAngle(double enemyBaseAngle) 
	{
		if(enemyBaseAngle < 0) // If the angle is between -180 and 0, tranform it in an angle between 0 and 360
		{
			enemyBaseAngle += 360;
		}
		this.enemyBaseAngle = enemyBaseAngle;
	}

	/**
	 * Return the distance between the agent and the enemy base 
	 * @return Return the distance between the agent and enemy base
	 */
	public double getEnemyBaseDistance() 
	{
		return enemyBaseDistance;
	}
	
	/**
	 * Set the distance between the agent and the enemy base	
	 * @param enemyBaseDistance A distance between the agent and the enemy base
	 */
	public void setEnemyBaseDistance(double enemyBaseDistance) 
	{
		this.enemyBaseDistance = enemyBaseDistance;
	}
}
