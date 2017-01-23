package environment;
import lifeform.MockLifeForm;
import static org.junit.Assert.*;
import org.junit.Test;
import weapon.*;

/**
 * The test cases for the Cell classes
 * @author David Reichard
 * @author Josh Varone - added Lab 5 Singleton tests
 *
 */
public class TestCell
{
	/**
	 * At initialization, the Cell should be empty and not contain a
	 * LifeForm.
	 * @author Josh Varone - check that the Cell contains no weapons
	 */
	@Test
	public void testInitialization()
	{
		Cell cell = new Cell();
		assertNull(cell.getLifeForm());
		assertNull(cell.getWeapon(1));
		assertNull(cell.getWeapon(2));
	}
	
	/**
	 * Tests that two weapons can be added to the Cell.
	 * @author Josh Varone
	 */
	@Test
	public void testAddWeapons()
	{
		Cell cell = new Cell();
		Weapon wp1 = new Pistol();
		Weapon wp2 = new PlasmaCannon();
		assertTrue(cell.addWeapon(wp1));
		assertEquals(wp1, cell.getWeapon(1));
		assertTrue(cell.addWeapon(wp2));
		assertEquals(wp2, cell.getWeapon(2));
	}
	
	/**
	 * Tests that weapons can be removed from the Cell.
	 * @author Josh Varone
	 */
	@Test
	public void testRemoveWeapons()
	{
		Cell cell = new Cell();
		Weapon wp1 = new Pistol();
		Weapon wp2 = new PlasmaCannon();
		cell.addWeapon(wp1);
		cell.addWeapon(wp2);
		//both weapons should be held in the cell
		assertEquals(wp1, cell.getWeapon(1));
		assertEquals(wp2, cell.getWeapon(2));
		//removing each weapon should make both positions null
		assertEquals(wp1, cell.removeWeapon(1));
		assertEquals(null, cell.getWeapon(1));
		assertEquals(wp2, cell.removeWeapon(2));
		assertEquals(null, cell.getWeapon(2));
	}
	
	/**
	 * Tests that more than 2 weapons can't be added.
	 */
	@Test
	public void testNoAddIfWeaponAlreadyThere()
	{
		Cell cell = new Cell();
		Weapon wp1 = new Pistol();
		Weapon wp2 = new PlasmaCannon();
		Weapon wp3 = new ChainGun();
		assertTrue(cell.addWeapon(wp1));
		assertTrue(cell.addWeapon(wp2));
		//after two weapons are added, additional ones should fail
		assertFalse(cell.addWeapon(wp3));
		//original two weapons should be retained
		assertEquals(wp1, cell.getWeapon(1));
		assertEquals(wp2, cell.getWeapon(2));
	}
	
	/********************************
	 * START Strategy Pattern Tests *
	 ********************************/
	
	/**
	 * Checks to see if we change the LifeForm held by the Cell that
	 * getLifeForm properly responds to this change.
	 */
	@Test
	public void testSetLifeForm()
	{
		MockLifeForm bob = new MockLifeForm("Bob", 40);
		MockLifeForm fred = new MockLifeForm("Fred", 40);
		Cell cell = new Cell();
		// The cell is empty so this should work.
		boolean success = cell.addLifeForm(bob);
		assertTrue(success);
		assertEquals(bob, cell.getLifeForm());
		// The cell is not empty so this should fail.
		success = cell.addLifeForm(fred);
		assertFalse(success);
		assertEquals(bob, cell.getLifeForm());
	}
	
	/**
	 * Test that we can remove a life form from the cell.
	 */
	@Test
	public void testRemoveLifeForm()
	{
		MockLifeForm entity = new MockLifeForm("Bob", 40);
		Cell cell = new Cell();
		cell.addLifeForm(entity);
		
		// make sure the entity is in the cell
		assertEquals(entity, cell.getLifeForm());
		
		// and now ensure that we successfully remove it
		// and that the life form is null afterwards
		assertTrue(cell.removeLifeForm());
		assertNull(cell.getLifeForm());
	}
}
