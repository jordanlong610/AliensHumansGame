package ui.command;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import lifeform.MockLifeForm;
import weapon.Pistol;
import weapon.Weapon;
/**
 * tests the functionality of the drop class
 * @author Jeff Titanich
 */
public class TestDrop
{
	
	
	//	LAB 6 (COMMAND) TESTS BEGIN HERE
	
	
	Environment e = Environment.getWorldInstance(9, 9);
	MockLifeForm m = new MockLifeForm("bob", 10);
	
	//	set up the Environment for all tests
	@Before
	public void before()
	{
		Pistol p = new Pistol();
		m.setWeapon(p);
		e.addLifeForm(m, 4, 4);
	}
	
	//	clean up the Environment after all tests
	@After
	public void cleanup()
	{
		e.clearEnvironment();
	}
	
	//	a LifeForm can drop their Weapon
	@Test
	public void testLifeFormDropWeapon()
	{
		Command drop = new Drop(e);
		drop.execute(4, 4);
		assertNull(m.getWeapon());
	}
	
	//	the Weapon dropped gets added to the current Cell in the Environment
	@Test
	public void testDropWeaponIntoCell()
	{
		Weapon wep = m.getWeapon();
		Command drop = new Drop(e);
		drop.execute(4,  4);
		assertEquals(wep, e.getWeapon(1, 4, 4));
	}
	
	//	can't drop into a Cell with both Weapon slots full
	@Test
	public void testDropIntoFullCell()
	{
		Weapon wep = m.getWeapon();
		Weapon p2 = new Pistol();
		Weapon p3 = new Pistol();
		e.addWeapon(p2, 4, 4);
		e.addWeapon(p3, 4, 4);
		Command drop = new Drop(e);
		drop.execute(4, 4);
		
		//	p2 and p3 should still be in the Cell and the LifeForm
		//	should still be holding wep.
		assertEquals(wep, m.getWeapon());
		assertEquals(p2, e.getWeapon(1, 4, 4));
		assertEquals(p3, e.getWeapon(2, 4, 4));
	}

}
