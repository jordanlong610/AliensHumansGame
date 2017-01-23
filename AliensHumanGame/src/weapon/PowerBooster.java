package weapon;

import exceptions.TooManyAttachmentsException;

/**
 * Provides functionality enhancements of a power booster for the Weapon
 * 
 * @author Joshua Varone
 *
 */
public class PowerBooster extends Attachment 
{
	/**
	 * Creates an instance of the power booster
	 * @param weapon the weapon to which the booster is added
	 * @throws TooManyAttachmentsException 
	 */
	public PowerBooster(Weapon weapon) throws TooManyAttachmentsException {
		super(weapon);
	}
	
	/**
	 * Gets the damage per shot of weapon with power booster
	 * @return the damage per shot of weapon with power booster
	 */
	@Override
	public int getDamage(int distance) {
		double tempDamage;
		tempDamage = (weapon.getDamage(distance))*
					 (1+((double)weapon.getCurrentAmmo()/(double)weapon.getMaxAmmo()));
		return (int)tempDamage;
	}
	
	/**
	 * Gets the description of weapon with power booster
	 * @return the description of the weapon with power booster
	 */
	@Override
	public String getDescription() {
		return weapon.getDescription() + ", Power Booster";
	}
}
