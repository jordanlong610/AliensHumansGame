package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.OutOfAmmoException;
/**
 * Tests the functionality of the Chain Gun
 * @author James Hall
 *
 */
public class TestChainGun 
{
	/*******
	 * EXTRA CREDIT tests for Decorator Lab
	 *******/
	
	/**
	 * Test to make sure that all values are initialized properly
	 */
	@Test
	public void testInitialization() 
	{
		ChainGun cg = new ChainGun();
		//Checks baseDamage
		assertEquals(15, cg.getDamage(30));
		assertEquals(4, cg.getRateOfFire());
		assertEquals(40, cg.getCurrentAmmo());
		assertEquals(40, cg.getMaxAmmo());
		assertEquals(30, cg.getMaxRange());
		assertEquals("Chain Gun", cg.getDescription());
	}
	/**
	 * Test that the chain gun does appropriate damage based on range.
	 * 
	 * Damage algorithm is base damage * ((currentAmmo/max range)
	 */
	@Test
	public void testDamageInRange()
	{
		ChainGun cg = new ChainGun();
		assertEquals(0, cg.getDamage(0)); 
		assertEquals(15, cg.getDamage(30));
	}
	
	/**
	 * Test that when fired past max range does no damage.
	 */
	@Test
	public void testDamageOutOfRange() 
	{
		ChainGun cg = new ChainGun();
		assertEquals(0, cg.getDamage(31));
	}

	/**
	 * Test that firing the weapon uses ammunition.
	 */
	@Test
	public void testFiringGun() throws OutOfAmmoException
	{
		ChainGun cg = new ChainGun();
		assertEquals(40, cg.getCurrentAmmo());
		cg.fire();
		assertEquals(39, cg.getCurrentAmmo());
	}
	
	/**
	 * Test reloading functionality of weapon.
	 */
	@Test
	public void testReload() throws OutOfAmmoException
	{
		ChainGun cg = new ChainGun();
		cg.fire();
		cg.fire();
		assertEquals(38, cg.getCurrentAmmo());
		cg.reload();
		assertEquals(cg.getMaxAmmo(), cg.getCurrentAmmo());
	}
}
