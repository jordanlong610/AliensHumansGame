package weapon;

/**
 * Pistol weapon. Damage increases the closer it is to target.
 * @author David Reichard
 */
public class Pistol extends GenericWeapon
{
	/**
	 * Initialize Pistol with default values.
	 */
	public Pistol()
	{
		baseDamage = 10;
		fireRate = 2;
		maxAmmo = 10;
		currentAmmo = 10;
		maxRange = 25;
	}
	
	/**
	 * @return damage of weapon based upon distance
	 */
	@Override
	public int getDamage(int distance) 
	{
		if (distance > maxRange)
			return 0;
		
		return (int)(baseDamage * ((maxRange - distance + 5) / (double)maxRange));
	}

	/**
	 * @return String containing description of weapon.
	 */
	@Override
	public String getDescription() 
	{
		return "Pistol";
	}
}
