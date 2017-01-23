package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.TooManyAttachmentsException;

/**
 * Test the functionality of the Scope class
 * 
 * @author James Hall
 */
public class TestScope 
{
	/*******
	 * EXTRA CREDIT tests for Decorator Lab
	 *******/
	
	/**
	 * Test to see that the gun can be initialized properly
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testInitialization() throws TooManyAttachmentsException 
	{
		Weapon w = new Scope(new Pistol());
		assertEquals("Pistol, Scope", w.getDescription());
		assertEquals(35, w.getMaxRange());
	}

	/**
	 * Test to see the new damage is correct when in old Range
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testDamageWithinOldRange() throws TooManyAttachmentsException 
	{
		Weapon w = new Pistol();
		assertEquals(10, w.getDamage(5));
		w = new Scope(w);
		assertEquals(18, w.getDamage(5));
	}

	/**
	 * Test to see that damage is tested properly with New Range
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testDamageInAmpedRange() throws TooManyAttachmentsException 
	{
		Weapon w = new Pistol();
		assertEquals(0, w.getDamage(26));
		w = new Scope(w);
		assertEquals(7, w.getDamage(26));
	}

	/**
	 * Testing a double Scope
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testDoubleScope() throws TooManyAttachmentsException 
	{
		Weapon w = new Scope(new Scope(new Pistol()));
		assertEquals("Pistol, Scope, Scope", w.getDescription());
		assertEquals(45, w.getMaxRange());
		assertEquals(12, w.getDamage(36));
		assertEquals(8, w.getDamage(35));
		assertEquals(0, w.getDamage(46));
	}

	/**
	 * Testing Pistol with 2 attachments (Scope + Stabilizer)
	 * 
	 * @author David Reichard
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testScopePlusStabilizer() throws TooManyAttachmentsException 
	{
		Weapon w = new Stabilizer(new Scope(new Pistol()));

		// pistol does 12 at 0 range, scope should double that based on formula,
		// and stabilizer should add 25% addition to that number
		// 12 * 2 = 24 + (24*.25) = 30
		assertEquals(30, w.getDamage(0));

		// pistol does 2 damage at max range, scope and stabilizer isn't enough
		// to push it past that
		assertEquals(2, w.getDamage(25));

		// when we go past max range, that is where the fun with scope begins
		// with scope damage should be base damage at max range (2) + 5 = 7,
		// then stabilizer
		// should add 25% to that resulting in 8 damage
		assertEquals(8, w.getDamage(35));
	}
}
