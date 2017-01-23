package ui.command;

import lifeform.LifeForm;
import environment.Environment;

/**
 * Changes player direction to West when called.
 * @author Jordan Long, Jeff Titanich
 *
 */

public class turnWest implements Command
{
	
	private LifeForm lf;
	private Environment environment;
	
	//Constructor for turnWest.
	public turnWest(Environment environment)
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
			lf.changeDirection("West");
		}
		
	}
	
	
	
}
