package ui.command;

import lifeform.LifeForm;
import environment.Environment;

/**
 * Changes player direction to South when called.
 * @author Jordan Long, Jeff Titanich
 *
 */

public class turnSouth implements Command
{
	
	private LifeForm lf;
	private Environment environment;
	
	//Constructor for turnSouth.
	public turnSouth(Environment environment)
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
			lf.changeDirection("South");
		}
		
	}
	
	
	
}
