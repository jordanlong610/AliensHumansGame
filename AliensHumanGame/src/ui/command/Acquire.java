package ui.command;

import javax.swing.JOptionPane;

import weapon.Weapon;
import lifeform.LifeForm;
import environment.Environment;

/**
 * 
 * Selected LifeForm picks up a weapon, but only if one exists in the Cell. If the LifeForm is already holding a weapon, 
 * it will swap the old weapon for the new weapon. This will keep the LifeForm from having to move to a different cell, 
 * drop a weapon, then return to this cell to get a new one when there are two weapons available. This command will try 
 * and get the weapon in slot 1 first, and if none there the weapon in slot 2.
 * 
 * @authors Jordan Long, Jeff Titanich, Josh Varone (added user choice for multiple weapons)
 */
public class Acquire implements Command
{
	
	private LifeForm lf;
	private Environment environment;
	
	//Constructor for Acquire.
	public Acquire(Environment environment)
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
			Weapon swapWeapon;
			Weapon currentWeapon;
			int weaponSlot = 0;
		
			//If weapon in both, let user choose which weapon
			if(environment.getWeapon(1, row, col) != null && environment.getWeapon(2, row, col) != null)
			{
				weaponSlot = getWeaponToChoose(row, col);
			}
			//Else, if weapon only in slot 1, use that
			else if(environment.getWeapon(1, row, col) != null)
			{
				weaponSlot = 1;
			}
			//Else, if weapon only in slot 2, use that
			else if(environment.getWeapon(2, row, col) != null)
			{
				weaponSlot = 2;
			}
			//Otherwise, no weapons in Environment, return
			else
			{
				return;
			}
		
			//Assigns respective weapons to temp holders.
			swapWeapon = environment.getWeapon(weaponSlot, row, col);
			currentWeapon = lf.getWeapon();
			
			//Set the new Weapon to LifeForm
			lf.dropWeapon();
			lf.setWeapon(swapWeapon);
			
			//Removes old weapon from cell and replaces it with new one.
			environment.removeWeapon(weaponSlot, row, col);
			environment.addWeapon(currentWeapon, row, col);
		}
	}
	
	/**
	 * Gets which weapon slot the user wants the LifeForm to pick up from.
	 * @return the user's choice of which weapon to pick up
	 * @author Josh Varone
	 */
	private int getWeaponToChoose(int row, int col)
	{
		//This allows the user to select the weapon slot to choose from
		//Creates the list of weapon slots
		String[] options = new String[2];
		options[0] = environment.getWeapon(1, row, col).getDescription();
		options[1] = environment.getWeapon(2, row, col).getDescription();
		String choice = (String) JOptionPane.showInputDialog(null, "Select which weapon to choose:", "Choose Weapon", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if(choice.equals(options[0]))
			return 1;
		return 2;
	}
}
