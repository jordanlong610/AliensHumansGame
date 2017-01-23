package weapon;

import exceptions.OutOfAmmoException;
import gameplay.TimeObserver;

/**
 * 
 * @author David Reichard
 * @author James Hall
 * @author Joshua Varone
 *
 */
public interface Weapon extends TimeObserver
{
	/**
	 * Get the maximum range of a weapon.
	 * @return maximum range
	 */
	public int getMaxRange();
	
	/**
	 * Get damage per shot of weapon.
	 * @return damage per shot
	 */
	public int getDamage(int distance);
	
	/**
	 * Get current ammunition of weapon.
	 * @return current ammo
	 */
	public int getCurrentAmmo();
	
	/**
	 * Get maximum ammunition of weapon
	 * @return max ammo
	 */
	public int getMaxAmmo();
	
	/**
	 * Get rate of fire for weapon
	 * @return rate of fire
	 */
	public int getRateOfFire();
	
	/**
	 * Get description of weapon with all applicable attachments
	 * @return description
	 */
	public String getDescription();
	
	/**
	 * Fire the weapon.
	 * @throws OutOfAmmoException If attempting to fire weapon when out of ammo.
	 */
	public void fire() throws OutOfAmmoException;
	
	/**
	 * Reload the weapon.
	 */
	public void reload();
}