package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.OutOfAmmoException;
import exceptions.TooManyAttachmentsException;

/**
 * Tests functionality provided by the PowerBooster class.
 * @author Joshua Varone
 *
 */
public class TestPowerBooster 
{
	/*******
	 * EXTRA CREDIT tests for Decorator Lab
	 *******/
	
	/**
	 * Test the initialization of a PowerBooster.
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testInitialization() throws TooManyAttachmentsException 
	{
		Weapon weapon = new PowerBooster(new ChainGun());
		assertEquals("Chain Gun, Power Booster", weapon.getDescription());
	}
	
	/**
	 * Test that the damage is calculated properly with a PowerBooster (1 attachment).
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testPowerBoosterDamage() throws OutOfAmmoException, TooManyAttachmentsException 
	{
		Weapon weapon = new ChainGun();
		//at 15 feet, ChainGun damage should be 7
		assertEquals(7, weapon.getDamage(15));
		weapon = new PowerBooster(weapon);
		//at full ammo, PowerBooster should increase damage 2 times to 14
		assertEquals(14, weapon.getDamage(15));
		weapon.fire();
		weapon.fire();
		//now, PowerBooster should increase damage 1.95 times to 13 (truncated)
		assertEquals(13, weapon.getDamage(15));
	}
	
	/**
	 * Test that Scope + PowerBooster behaves correctly.
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testScopePowerBooster() throws OutOfAmmoException, TooManyAttachmentsException
	{
		Weapon weapon = new Pistol();
		assertEquals(12, weapon.getDamage(0));
		weapon = new PowerBooster(new Scope(weapon));
		assertEquals(35, weapon.getMaxRange());
		//Scope should improve damage from 12 to 24
		//PowerBooster should improve it to 48
		assertEquals(48, weapon.getDamage(0));
		
		//at original max range, pistol damage is 2.
		//Scope doesn't change this; PowerBooster doubles to 4
		assertEquals(4, weapon.getDamage(25));
		
		//at extended max range, Scope damage is 7.
		//PowerBooster should double to 14.
		assertEquals(14, weapon.getDamage(35));
		
		weapon.fire();
		//after firing a round, scope should double damage to 24
		//and power booster should increase it to 45
		assertEquals(45, weapon.getDamage(0));
	}
	
	/**
	 * Test that Stabilizer + PowerBooster behaves correctly.
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testStabilizerPowerBooster() throws OutOfAmmoException, TooManyAttachmentsException
	{
		Weapon weapon = new PlasmaCannon();
		assertEquals(50, weapon.getDamage(0));
		weapon = new PowerBooster(new Stabilizer(weapon));
		//Stabilizer should improve damage from 50 to 62
		//PowerBooster should double that to 124
		assertEquals(124, weapon.getDamage(0));
		weapon.fire();
		//now, Stabilizer should improve damage from 37 to 46
		//PowerBooster should increase that to 80
		assertEquals(80, weapon.getDamage(0));
		weapon.updateTime(0);
		weapon.fire();
		weapon.updateTime(0);
		weapon.fire();
		weapon.updateTime(0);
		weapon.fire();
		//stabilizer should auto reload
		assertEquals(weapon.getMaxAmmo(), weapon.getCurrentAmmo());
	}
	
	/**
	 * Test that PowerBooster + PowerBooster behaves correctly.
	 * @throws TooManyAttachmentsException 
	 */
	@Test
	public void testPowerBoosterPowerBooster() throws OutOfAmmoException, TooManyAttachmentsException
	{
		Weapon weapon = new ChainGun();
		assertEquals(15, weapon.getDamage(30));
		weapon = new PowerBooster(new PowerBooster(weapon));
		//PowerBooster should double damage to 30
		//PowerBooster should double it again to 60
		assertEquals(60, weapon.getDamage(30));
		weapon.fire();
		//Now damage should be increased 1.975 times to 29
		//and 1.975 times again to 57
		assertEquals(57, weapon.getDamage(30));
	}
}
