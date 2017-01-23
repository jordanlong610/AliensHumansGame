package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.OutOfAmmoException;
import exceptions.TooManyAttachmentsException;

/**
 * Test the stabilizer attachment.
 * @author David Reichard
 *
 */
public class TestStabilizer 
{
	/*******
	 * EXTRA CREDIT tests for Decorator Lab
	 *******/
	
	/**
	 * Test initialization.
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testInitialization() throws TooManyAttachmentsException
	{
		Weapon w = new Stabilizer(new PlasmaCannon());
		assertEquals("Plasma Cannon, Stabilizer", w.getDescription());
	}
	
	/**
	 * Test the stabilizer attachment's auto-reload ability.
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testAutoReload() throws OutOfAmmoException, TooManyAttachmentsException 
	{
		Weapon w = new Stabilizer(new PlasmaCannon());
		
		assertEquals(4, w.getCurrentAmmo());
		for (int i = 0; i <= 3; i++)
		{
			w.fire(); // fire all 4 rounds
			w.updateTime(0);
		}
		
		// now assert that the weapon auto-reloaded
		assertEquals(4, w.getCurrentAmmo());
	}
	
	/**
	 * Testing Plasma Cannon with 1 attachment (Stabilizer)
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testModifyDamageProperly() throws TooManyAttachmentsException
	{
		Weapon w = new PlasmaCannon();
		assertEquals(50, w.getDamage(1)); // plasma cannon should do 50 at max ammo within range
		
		// now attach stabilizer, which should provide a 25% boost to existing damage
		w = new Stabilizer(w);
		assertEquals(62, w.getDamage(1));
	}
	
	/**
	 * Testing Plasma Cannon with 2 attachments (Stabilizer + Stabilizer)
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testDoubleStabilizer() throws TooManyAttachmentsException
	{
		Weapon w = new PlasmaCannon();
		assertEquals(50, w.getDamage(1)); // plasma cannon should do 50 at max ammo within range
		
		// attack 2 stabilizers, should provide a 50% boost to existing damage
		// be careful, this is stacked so the first one will modify by 25%, then the second one
		// will modify the existing modified damage by an additional 25%,
		// RESULTING IN MORE THAN A 50% INCREASE TO BASE DAMAGE
		w = new Stabilizer(new Stabilizer(w));
		assertEquals(77, w.getDamage(1));
	}
	
	/**
	 * Testing Chain Gun with 2 attachments (Power Booster + Stabilizer)
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testPowerBoosterPlusStabilizer() throws TooManyAttachmentsException
	{
		Weapon w = new Stabilizer(new PowerBooster(new ChainGun()));
		
		// with a full chamber, power booster should double damage
		// then the stabilizer will increase that by 25%
		// The chain gun does base damage (15) at max range (30 feet), so let's start there
		assertEquals(37, w.getDamage(30));
	}
}
