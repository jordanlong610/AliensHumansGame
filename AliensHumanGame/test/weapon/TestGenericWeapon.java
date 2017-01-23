package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.OutOfAmmoException;
import gameplay.SimpleTimer;

/**
 * Test case for testing the GenericWeapon abstract class.
 * @author David Reichard
 *
 */
public class TestGenericWeapon 
{
	/**
	 * MockWeapon used for testing the abstract GenericWeapon class.
	 * @author David Reichard
	 *
	 */
	private class MockWeapon extends GenericWeapon
	{
		/**
		 * Start off the mock weapon with some simple values.
		 */
		public MockWeapon()
		{
			baseDamage = 6;
			fireRate = 2;
			maxAmmo = 10;
			currentAmmo = 10;
			maxRange = 2;
		}

		/**
		 * Return base damage.
		 */
		public int getDamage(int distance) 
		{
			return distance <= maxRange ? baseDamage : 0;
		}

		/**
		 * Return description of mock weapon.
		 */
		public String getDescription() 
		{
			return "Mock Weapon";
		}
	}

	/**
	 * Test initialization and getters of a generic mock weapon. We do not have to test
	 * damage retrieval or description getters since they are abstract methods,
	 * and subclasses are forced to implement them individually.
	 */
	@Test
	public void testInitialization() 
	{
		MockWeapon w = new MockWeapon();
		assertEquals(6, w.getBaseDamage());
		assertEquals(2, w.getRateOfFire());
		assertEquals(10, w.getMaxAmmo());
		assertEquals(10, w.getCurrentAmmo());
		assertEquals(2, w.getMaxRange());
		assertEquals(6, w.getDamage(0));
		assertEquals("Mock Weapon", w.getDescription());
	}
	
	/**
	 * Test that firing the weapon expels ammunition.
	 */
	@Test
	public void testUseAmmo() throws OutOfAmmoException
	{
		MockWeapon w = new MockWeapon();
		assertEquals(10, w.getCurrentAmmo());
		w.fire();
		assertEquals(9, w.getCurrentAmmo());
	}
	
	/**
	 * Make sure we can't fire past our fire rate in any single round.
	 */
	@Test
	public void testFireRate() throws OutOfAmmoException
	{
		Weapon w = new MockWeapon();
		SimpleTimer timer = new SimpleTimer();
		timer.addTimeObserver(w);
		
		/** 
		 * MockWeapon has a fire rate of 2, so assert our current status,
		 * fire twice, and assert we are 2 less rounds in the chamber.
		 */
		assertEquals(10, w.getCurrentAmmo());
		w.fire();
		w.fire();
		assertEquals(8, w.getCurrentAmmo());
		
		/**
		 * Now fire and assert we are still at 2 less rounds in the chamber
		 * since we should have reached our max fire rate.
		 */
		w.fire();
		assertEquals(8, w.getCurrentAmmo());
		
		/**
		 * Advance to the next round and assert that we may now fire again.
		 */
		timer.timeChanged();
		w.fire();
		assertEquals(7, w.getCurrentAmmo());
	}
	
	/**
	 * Test reloading functionality of weapon.
	 */
	@Test
	public void testReload() throws OutOfAmmoException
	{
		Weapon w = new MockWeapon();
		w.fire();
		w.fire();
		assertEquals(8, w.getCurrentAmmo());
		w.reload();
		assertEquals(w.getMaxAmmo(), w.getCurrentAmmo());
	}
	
	/**
	 * Test that an OutOfAmmo exception is thrown if the weapon
	 * is out of ammunition and we attempt to fire.
	 * 
	 * Rubric states that fire does no damage when out of ammo, this is
	 * true by the fact that we throw an exception to be handled by caller
	 * if the weapon is out of ammunition, instead of just returning 0.
	 */
	@Test (expected = OutOfAmmoException.class)
	public void testOutOfAmmo() throws OutOfAmmoException
	{
		Weapon w = new MockWeapon();
		for (int i = 0; i <= 9; i++)
		{
			w.updateTime(0); // reset round each time so we don't run into fire rate problems
			w.fire(); // fire 10 times
		}
		assertEquals(0, w.getCurrentAmmo());
		w.fire(); // this should throw an OutOfAmmo exception
	}
	
	/**
	 * Test that the weapon does no damage (but does fire) if target is
	 * beyond max range.
	 */
	@Test
	public void testNoDamageButFiresAmmoOutOfRange() throws OutOfAmmoException
	{
		Weapon w = new MockWeapon();
		assertEquals(10, w.getCurrentAmmo());
		w.fire();
		
		// damage should be 0 past max range, but should have still expelled a round
		assertEquals(0, w.getDamage(3));
		assertEquals(9, w.getCurrentAmmo());
	}
}
