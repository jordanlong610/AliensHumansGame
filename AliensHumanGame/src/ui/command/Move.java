package ui.command;

import lifeform.*;
import environment.Environment;
import javax.swing.JOptionPane;

/**
 * Attempts to move the selected LifeForm maxSpeed spaces in the direction it is facing using the movement rules of the Environment.
 * @authors Jordan Long, Jeff Titanich, Josh Varone (getSpacesToMove method)
 */
public class Move implements Command
{

	private LifeForm lf;
	private Environment environment;
	
	//Constructor for Move.
	public Move(Environment environment)
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
			environment.move(lf, getSpacesToMove(lf));
		}
	}
	
	/**
	 * Gets the number of spaces to move from a dialog box (user-specified).
	 * @return the user's choice of how many spaces to move
	 * @author Josh Varone
	 */
	private int getSpacesToMove(LifeForm lf)
	{
		//This allows the user to select spaces to move
		//Creates the list of possible spaces to move
		Integer[] options = new Integer[lf.getMaxSpeed()];
		for(int i = 0; i < options.length; i++)
			options[i] = i+1;		
		return (int) JOptionPane.showInputDialog(null, "Select how many spaces to move:", "Move LifeForm", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
}
