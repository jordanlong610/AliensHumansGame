package weapon;

/**
 * Provides Functionality for the ChainGun
 * 
 * @author James Hall
 *
 */
public class ChainGun extends GenericWeapon 
{
	/**
	 * Creates an instance of the Chain Gun loading it with its default values
	 */
	public ChainGun() 
	{
		baseDamage = 15;
		fireRate = 4;
		maxAmmo = 40;
		currentAmmo = 40;
		maxRange = 30;

	}

	/**
	 * Calculates the damage for the Chain Gun
	 * 
	 * @param the
	 *            distance the target is from the user
	 * @return the damage to be inflicted
	 */
	@Override
	public int getDamage(int distance) 
	{
		if (distance <= maxRange) {
			float tempDamage = 0.0f;
			tempDamage = baseDamage * (distance / (float) (maxRange));

			return (int) (tempDamage);
		} else {
			return 0;
		}
	}

	/**
	 * Returns the description of the Chain Gun
	 */
	@Override
	public String getDescription() 
	{
		return "Chain Gun";
	}
}
