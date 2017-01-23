package weapon;

import exceptions.OutOfAmmoException;

/**
 * Generic weapon that all weapons should inherit from.
 * @author David Reichard
 *
 */
public abstract class GenericWeapon implements Weapon
{
	protected int baseDamage;
	protected int fireRate;	
	protected int maxAmmo;	
	protected int currentAmmo;
	protected int maxRange;
	private int timesFired;
	
	/**
	 * Get damage caused by the weapon. Should override in subclasses.
	 */
	@Override
	public abstract int getDamage(int distance);
	
	/**
	 * Get description of the weapon. Should override in subclasses.
	 */
	@Override
	public abstract String getDescription();
		
	/**
	 * @return maximum range of the weapon.
	 */
	@Override
	public int getMaxRange() 
	{
		return maxRange;
	}
	
	/**
	 * @return current ammo
	 */
	@Override
	public int getCurrentAmmo() 
	{
		return currentAmmo;
	}

	/**
	 * @return max ammo
	 */
	@Override
	public int getMaxAmmo() 
	{
		return maxAmmo;
	}
	
	/**
	 * @return rate of fire for weapon
	 */
	@Override
	public int getRateOfFire() 
	{
		return fireRate;
	}
	
	/**
	 * @return base damage of weapon
	 */
	public int getBaseDamage()
	{
		return baseDamage;
	}
	
	/**
	 * Fire a weapon, expelling ammunition.
	 */
	@Override
	public void fire() throws OutOfAmmoException 
	{
		if (timesFired >= fireRate)
			return; // If we have reached our max fire rate for the round, do nothing.
		
		if (currentAmmo <= 0)
			throw new OutOfAmmoException();
		
		timesFired++;
		currentAmmo--;
	}

	/**
	 * Reload weapon to max ammo.
	 */
	@Override
	public void reload() 
	{
		currentAmmo = maxAmmo;
	}
	
	/**
	 * Reset timesFired to 0 every round change.
	 */
	@Override
	public void updateTime(int round)
	{
		timesFired = 0; // reset times fired this round to 0
	}
}
