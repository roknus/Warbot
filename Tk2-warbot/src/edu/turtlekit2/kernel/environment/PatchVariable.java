/*
 * TurtleKit - An Artificial Life Simulation Platform
 * Copyright (C) 2000-2010 Fabien Michel, Gregory Beurier
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package edu.turtlekit2.kernel.environment;

/**Use this class to create a numeric patch variable (double). Having created one,
   you can set its evaporation and diffusion coefficients to a specific percentage
	(0 <= coef <= 1). You can also give a default value for this flavor (when the simulation
	starts each patch has its own flavor with the given default value).
	Evaporation simply consists in decreasing the value.
	Diffusion makes each patch share a percentage of a value with
	its eight neighboring patches.

    @author Fabien MICHEL
    @version 1.2 4/1/2001 */

final public class PatchVariable
{
	public double evaporation=0;
	public double diffCoef=0;
	public String name;
	double defaultV=0;

	public PatchVariable(String name)
	{
		this.name=name;
		System.err.println("New Flavor: " + name);
	}
	final public void setEvapCoef(double evapCoef)
	{
		if (evapCoef<0 || evapCoef>1)
			System.err.println("You should set correctly the evapCoef (0<=ec<=1)");
		evaporation=evapCoef;
	}
	final public void setDiffuseCoef(double diffuseCoef)
	{
		if (diffuseCoef<0 || diffuseCoef>1)
			System.err.println("You should set correctly the diffuseCoef (0<=ec<=1)");
		diffCoef=diffuseCoef;
	}
	final public void setDefaultValue(double defaultValue)
	{
		defaultV= defaultValue;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
}

