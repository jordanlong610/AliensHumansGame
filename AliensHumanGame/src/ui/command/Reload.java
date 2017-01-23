package ui.command;

import lifeform.LifeForm;
import environment.Environment;

/**
 * Reloads the weapon the LifeForm is holding. If the LifeForm is not holding
 * a weapon, then nothing happens
 * @author Jordan Long, Jeff Titanich
 *
 */
public class Reload implements Command
{

	private LifeForm lf;
	private Environment environment;
	
	//Constructor for Reload.
	public Reload(Environment environment)
	{
		this.environment = environment;
	}
	
	
	@Override
	public void execute(int row, int col)
	{
		//Checks if there is a LifeForm in Cell
		lf = environment.getLifeForm(row, col);
		if (lf == null)
				{
					return;
				}
		else
		{
			//If LifeForm is not holding weapon, then method returns.
			if(lf.getWeapon()==null)
			{
				return;
			}
			else
			{
				//If LifeForm is holding weapon, then it will reload it.
				lf.getWeapon().reload();
			}
		}
	}
}
