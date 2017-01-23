package weapon;

/**
 * Provides functionality for the Plasma Cannon weapon
 * 
 * @author Joshua Varone
 *
 */
public class PlasmaCannon extends GenericWeapon 
{
	/**
	 * Creates an instance of the Plasma Cannon with default values
	 */
	public PlasmaCannon() 
	{
		baseDamage = 50;
		fireRate = 1;
		maxAmmo = 4;
		currentAmmo = maxAmmo;
		maxRange = 20;
	}
	
	/**
	 * Gets the damage per shot of the plasma cannon
	 * @return damage per shot of the plasma cannon
	 */
	@Override
	public int getDamage(int distance) 
	{
		double tempDamage;
		if(distance <= maxRange && currentAmmo > 0) {
			tempDamage = (baseDamage)*((double)currentAmmo/(double)maxAmmo);
			return (int)tempDamage;
		}
		return 0;
	}
	
	/**
	 * Gets the description of the plasma cannon
	 * @return description of the plasma cannon
	 */
	@Override
	public String getDescription() 
	{
		return "Plasma Cannon";
	}
}
