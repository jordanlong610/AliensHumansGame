package ui.command;

import environment.Environment;
import lifeform.LifeForm;

/**
 * Changes player direction to East when called.
 * @author Jordan Long, Jeff Titanich
 *
 */

public class turnEast implements Command
{

	private LifeForm lf;
	private Environment environment;
	
	//Constructor for turnEast.
	public turnEast(Environment environment)
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
			lf.changeDirection("East");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
