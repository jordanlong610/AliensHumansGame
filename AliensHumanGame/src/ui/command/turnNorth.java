package ui.command;

import lifeform.LifeForm;
import environment.Environment;

/**
 * Changes player direction to North when called.
 * @author Jordan Long
 * @author Jeff Titanich
 */

public class turnNorth implements Command
{
	
	private LifeForm lf;
	private Environment environment;
	
	//Constructor for turnNorth.
	public turnNorth(Environment environment)
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
		else
		{
			lf.changeDirection("North");
		}
		
	}
	

}
