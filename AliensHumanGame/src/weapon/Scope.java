package weapon;

import exceptions.TooManyAttachmentsException;

/**
 * Increases the max Range by ten and increases the power of the gun that has it
 * equipped
 * 
 * @author James Hall
 *
 */
public class Scope extends Attachment {

	private int ampedMaxRange;

	/**
	 * Creates an instance of the Scope
	 * 
	 * @param weapon
	 *            - the weapon it is being attached to
	 * @throws TooManyAttachmentsException 
	 */
	public Scope(Weapon weapon) throws TooManyAttachmentsException {
		super(weapon);
		ampedMaxRange = weapon.getMaxRange() + 10;
	}

	/**
	 * Calculates the damage to be done with the scope equipped
	 * 
	 * @param the
	 *            distance away from the target
	 * @return the damage to be done
	 */
	@Override
	public int getDamage(int distance) {
		float tempDamage = 0;
		if ((distance > weapon.getMaxRange()) && (distance <= ampedMaxRange)) {
			tempDamage = weapon.getDamage(weapon.getMaxRange());
			tempDamage = tempDamage + 5;
		}
		if (distance <= weapon.getMaxRange()) {
			tempDamage = weapon.getDamage(distance);
			tempDamage = tempDamage* (1 + ((ampedMaxRange - distance) / ((float)(ampedMaxRange))));
		}
		return (int) (tempDamage);
	}

	/**
	 * Concatenates Scope onto the description of the weapon
	 * 
	 * @return the description of the weapon
	 */
	@Override
	public String getDescription() {

		return weapon.getDescription() + ", Scope";
	}

	/**
	 * @return the extended range
	 */
	@Override
	public int getMaxRange() {

		return ampedMaxRange;
	}

	/**
	 * Updates time with the weapon 
	 */
	@Override
	public void updateTime(int time) {
		weapon.updateTime(time);

	}
}
