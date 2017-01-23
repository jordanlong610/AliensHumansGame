package ui.command;

import weapon.Weapon;
import lifeform.LifeForm;
import environment.Environment;

/**
 * Selected LifeForm drops its current weapon. Should not allow the LifeForm to drop a weapon if no space for the weapon in the Cell.
 * @authors Jordan Long, Jeff Titanich
 */

public class Drop implements Command
{

	
	private LifeForm lf;
	private Environment environment;
	
	//Constructor for Reload.
	public Drop(Environment environment)
	{
		this.environment = environment;
	}
	
	@Override
	public void execute(int row, int col)
	{
		
		lf = environment.getLifeForm(row, col);
		if (lf == null)
		{
			return;
		}
		else if(environment.getWeapon(1, row, col) != null && environment.getWeapon(2, row, col) != null)
		{
			return;
		}
		else
		{
			Weapon currentWeapon;
			
			//	Checks to see if the LifeForm's Weapon can be dropped into
			//	Weapon slot 1 of the current Cell.
			if(environment.getWeapon(0, row, col)==null)
			{
				//Assigns respective weapons to temp holders.
				currentWeapon = lf.getWeapon();
	
				//Set LifeForm weapon to null, aka dropped.
				lf.setWeapon(null);
				
				//Adds dropped weapon to environment.
				environment.addWeapon(currentWeapon, row, col);
			}
			
			
			//	Checks to see if the LifeForm's Weapon can be dropped into
			//	Weapon slot 2 of the current Cell.
			else if(environment.getWeapon(1, row, col)==null)
			{
				//Assigns respective weapons to temp holders.
				currentWeapon = lf.getWeapon();
	
				//Set LifeForm weapon to null, aka dropped.
				lf.setWeapon(null);
				
				//Adds dropped weapon to environment.
				environment.addWeapon(currentWeapon, row, col);
			}	
		}
	}
}
