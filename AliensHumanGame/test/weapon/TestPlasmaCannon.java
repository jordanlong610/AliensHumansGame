package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.OutOfAmmoException;

/**
 * Provides test cases for the plasma cannon
 * 
 * @author Joshua Varone
 *
 */
public class TestPlasmaCannon 
{
	
	/*******
	 * EXTRA CREDIT tests for Decorator Lab
	 *******/

	/**
	 * Tests that correct damage is inflicted based on range.  Does
	 * not change amount of ammunition.
	 */
	@Test
	public void testCorrectDamageRange() 
	{
		PlasmaCannon weapon = new PlasmaCannon();
		//damage at a distance of 1 (< range) should return some damage
		assertEquals(50, weapon.getDamage(1));
		//damage at a distance of 20  (= range) should return some damage
		assertEquals(50, weapon.getDamage(20));
		//damage at a distance of 40 (> range) should return 0 damage
		assertEquals(0, weapon.getDamage(40));
	}
	
	/**
	 * Tests that correct damage is inflicted based on ammo.  No 
	 * damage should be inflicted when ammo is 0.
	 * @throws OutOfAmmoException if ammo is <= 0.
	 */
	@Test
	public void testCorrectDamageAmmo() throws OutOfAmmoException 
	{
		PlasmaCannon weapon = new PlasmaCannon();
		assertEquals(4, weapon.getCurrentAmmo());
		assertEquals(50, weapon.getDamage(10));
		weapon.fire();
		//damage with ammo of 3 should be 37.5, truncated to 37
		assertEquals(37, weapon.getDamage(10));
		weapon.updateTime(0);
		weapon.fire();
		//damage with ammo of 2 should be 25
		assertEquals(25, weapon.getDamage(10));
		weapon.updateTime(0);
		weapon.fire();
		//damage with ammo of 1 should be 12.5, truncated to 12
		assertEquals(12, weapon.getDamage(10));
		weapon.updateTime(0);
		weapon.fire();
		assertEquals(0, weapon.getCurrentAmmo());
		assertEquals(0, weapon.getDamage(10));
	}
}
