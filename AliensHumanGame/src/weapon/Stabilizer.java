package weapon;

import exceptions.OutOfAmmoException;
import exceptions.TooManyAttachmentsException;

/**
 * Stabilizer attachment, which provides an auto-reload functionality
 * and increases the damage done by 25% of base damage.
 * @author David Reichard
 *
 */
public class Stabilizer extends Attachment 
{
	/**
	 * Initialize stabilizer with existing weapon to attach to.
	 * @param weapon Weapon attachment is being attached to.
	 * @throws TooManyAttachmentsException 
	 */
	public Stabilizer(Weapon weapon) throws TooManyAttachmentsException 
	{
		super(weapon);
	}

	/**
	 * Implement auto-reloading ability.
	 */
	@Override
	public void fire() throws OutOfAmmoException
	{
		super.fire();
		if (this.getCurrentAmmo() == 0)
		{
			this.reload();
		}
	}
	
	/**
	 * Add a 25% damage boost to existing damage.
	 */
	@Override
	public int getDamage(int distance) 
	{
		return (int) (weapon.getDamage(distance) + (weapon.getDamage(distance) * 0.25));
	}

	/**
	 * Return base description + stabilizer description
	 */
	@Override
	public String getDescription() 
	{
		return weapon.getDescription() + ", Stabilizer";
	}

}
