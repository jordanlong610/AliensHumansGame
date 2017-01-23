package environment;
import weapon.Weapon;
import lifeform.LifeForm;

/**
 * A Cell that can hold a LifeForm.
 * @author David Reichard
 * @author Josh Varone - added Lab 5 Singleton functionality
 *
 */
public class Cell 
{
	private LifeForm lifeform;
	private Weapon[] weapons = new Weapon[2]; //Holds weapons in cell. Added Lab 5.
	
	/**
	 * @return the LifeForm in this Cell
	 */
	public LifeForm getLifeForm()
	{
		return lifeform;
	}

	/**
	 * Tries to add the LifeForm to the Cell. Will not add if a
	 * LifeForm is already present.
	 * @return true if the LifeForm was added to the Cell, false if otherwise.
	 */
	public boolean addLifeForm(LifeForm entity)
	{
		if (lifeform == null)
		{
			lifeform = entity;
			return true;
		}
		return false;
	}

	/**
	 * Tries to remove the LifeForm from the Cell.  Will not remove if no
	 * LifeForm is present.
	 * @return true if the LifeForm was removed from the Cell, false if otherwise.
	 */
	public boolean removeLifeForm()
	{
		if (lifeform == null)
			return false;
		lifeform = null;
		return true;
	}
	
	/**
	 * Adds a weapon to the first open spot in the cell.
	 * @param wp the weapon to be added to the cell
	 * @return true if successfully added, false otherwise
	 * @author Josh Varone
	 */
	public boolean addWeapon(Weapon wp)
	{
		if (weapons[0] == null) 
		{
			weapons[0] = wp;
			return true;
		}
		else if (weapons[1] == null)
		{
			weapons[1] = wp;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the weapon from the specified position from the cell.
	 * @param position the position to remove the weapon from
	 * @return the weapon removed from the cell
	 * @author Josh Varone
	 */
	public Weapon removeWeapon(int position)
	{
		if (position == 1 || position == 2)
		{
			Weapon tempWeapon = getWeapon(position);
			weapons[position-1] = null;
			return tempWeapon;
		}
		return null;
	}
	
	/**
	 * Gets the weapon at the specified position in the cell.
	 * @param position the position to get the weapon from
	 * @return the weapon at the given position
	 * @author Josh Varone
	 */
	public Weapon getWeapon (int position)
	{
		if (position == 1 || position == 2)
		{
			return weapons[position-1];
		}
		return null;
	}
}
