package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for functionality of Pistol weapon.
 * @author David Reichard
 *
 */
public class TestPistol 
{
	/*******
	 * EXTRA CREDIT tests for Decorator Lab
	 *******/
	
	/**
	 * Test initialization of Pistol.
	 */
	@Test
	public void testInitialization()
	{
		Pistol p = new Pistol();
		assertEquals(10, p.getBaseDamage());
		assertEquals(25, p.getMaxRange());
		assertEquals(2, p.getRateOfFire());
		assertEquals(10, p.getMaxAmmo());
		assertEquals(10, p.getCurrentAmmo());
		assertEquals("Pistol", p.getDescription());
	}
	
	/**
	 * Test that the pistol does appropriate damage based on range.
	 * 
	 * Damage algorithm is base damage * ((max range - distance + 5)/max range)
	 */
	@Test
	public void testDamagePerRange()
	{
		Pistol p = new Pistol();
		
		// max damage
		assertEquals(12, p.getDamage(0)); // range 0, 12
		
		// flooring properly
		assertEquals(11, p.getDamage(1)); // range 1, 11.6 should floor to 11
		
		// border case
		assertEquals(11, p.getDamage(2)); // range 2, 11.2 should floor to 11
		assertEquals(10, p.getDamage(3)); // range 3, 10.8 should floor to 10
		
		// make sure do damage at max range
		assertEquals(2, p.getDamage(25)); // range 25, 2
	}
	
	/**
	 * Test that when fired past max range does no damage.
	 */
	@Test
	public void testDamageTooFar() 
	{
		Pistol p = new Pistol();
		assertEquals(0, p.getDamage(26));
	}
}
