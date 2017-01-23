package weapon;

import exceptions.OutOfAmmoException;
import exceptions.TooManyAttachmentsException;

/**
 * Abstract definition of a weapon attachment.
 * 
 * @author David Reichard
 * @author James Hall
 * @author Joshua Varone
 *
 */
public abstract class Attachment implements Weapon
{
	protected Weapon weapon;
	
	/**
	 * Instantiate an instance of attachment with weapon attachment is being attached to.
	 * @param weapon Weapon attachment is being attached to.
	 * @throws TooManyAttachmentsException 
	 * @author James Hall
	 */
	public Attachment(Weapon weapon) throws TooManyAttachmentsException
	{
		String description = weapon.getDescription();
		int numOfAttach = 0;
		for(int i= 0; i <description.length(); i++)
		{
			if(description.charAt(i) == ',')
			{
				numOfAttach++;
			}
		}
		if(numOfAttach < 2)
		{
			this.weapon = weapon;
		}
		else
		{
			throw new TooManyAttachmentsException();
		}
	}
	
	/**
	 * Get damage per shot of weapon after attachment.
	 * @return damage per shot after attachment
	 */
	@Override
	public abstract int getDamage(int distance);
	
	/**
	 * Get the maximum range of a weapon after attachment.
	 * @return maximum range after attachment
	 */
	@Override
	public int getMaxRange()
	{
		return weapon.getMaxRange();
	}
	
	/**
	 * Get current ammunition of weapon.
	 * @return current ammo after attachment
	 */
	@Override
	public int getCurrentAmmo()
	{
		return weapon.getCurrentAmmo();
	}
	
	/**
	 * Get maximum ammunition of weapon after attachment.
	 * @return max ammo after attachment
	 */
	@Override
	public int getMaxAmmo()
	{
		return weapon.getMaxAmmo();
	}
	
	/**
	 * Get rate of fire for weapon after attachment.
	 * @return rate of fire after attachment
	 */
	@Override
	public int getRateOfFire()
	{
		return weapon.getRateOfFire();
	}
	
	/**
	 * Get description of weapon after attachment.
	 * @return description after attachment
	 */
	@Override
	public abstract String getDescription();
	
	/**
	 * Handles necessary value updates after the weapon is fired.
	 * @throws OutOfAmmoException If attempting to fire weapon when out of ammo.
	 */
	@Override
	public void fire() throws OutOfAmmoException 
	{
		weapon.fire();
	}
	
	/**
	 * Resets current ammo to the maximum ammo of the weapon after attachment.
	 */
	@Override
	public void reload() 
	{
		weapon.reload();
	}
	
	/**
	 * Call back to base weapon's update time for proper handling.
	 */
	@Override
	public void updateTime(int round)
	{
		weapon.updateTime(round);
	}
}
