package ui.command;

import lifeform.LifeForm;
import environment.Environment;
/**
 * Selected LifeForm attacks in the direction it is facing. To keep things simple a LifeForm will only 
 * attack targets in a direct line in front of the LifeForm. The LifeForm should only fire its weapon 
 * if there is a target. The LifeForm will always attack the closest enemy target.
 * 
 * @author Jordan Long
 * @author Jeff Titanich
 */
public class Attack implements Command
{

	private LifeForm lf;
	private Environment environment;
	
	//Constructor for Attack.
	public Attack(Environment environment)
	{
		this.environment = environment;
	}
	
	
	@Override
	public void execute(int row, int col)
	{
		LifeForm enemy = null;
		//Checks if there is a LifeForm in Cell
		lf = environment.getLifeForm(row, col);
		if (lf == null)
				{
					return;
				}
		else
		{
			//	if the LifeForm is facing north
			if(lf.getDirection() == "North")
			{
				//	find the closest lifeform
				for(int i = row - 1; i >= 0 && enemy == null; i--)
				{
					if(environment.getLifeForm(i, col) != null)
					{
						enemy = environment.getLifeForm(i,  col);
					}
				}
			}
			
			//	if the LifeForm is facing south
			if(lf.getDirection() == "South")
			{
				//	find the closest lifeform
				for(int i = row + 1; i < environment.getWorldRows() && enemy == null; i++)
				{
					if(environment.getLifeForm(i, col) != null)
					{
						enemy = environment.getLifeForm(i,  col);
					}
				}
			}
			
			//	if the LifeForm is facing east
			if(lf.getDirection() == "East")
			{
				//	find the closest lifeform
				for(int i = col + 1; i < environment.getWorldCols() && enemy == null; i++)
				{
					if(environment.getLifeForm(row, i) != null)
					{
						enemy = environment.getLifeForm(row,  i);
					}
				}
			}
			
			//	if the LifeForm is facing west
			if(lf.getDirection() == "West")
			{
				//	find the closest lifeform
				for(int i = col - 1; i >= 0 && enemy == null; i--)
				{
					if(environment.getLifeForm(row, i) != null)
					{
						enemy = environment.getLifeForm(row,  i);
					}
				}
			}
			
			//If no enemy in front of LifeForm, then it returns.
			if(enemy==null)
			{
				return;
			}
			
			//If there is an enemy, it will attack it. We can only attack an enemy that is directly in front of us, and it must be the closest one.
			else
			{
				lf.attack(enemy);
			}
			
		}
	}
}
