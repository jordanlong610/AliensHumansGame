package ui.command;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import lifeform.MockLifeForm;
import weapon.*;
/**
 * tests the functionality of the Acquire class
 * @author Jeff Titanich, Josh Varone (user-selection test in bonus)
 */
public class TestAcquire
{
	
	
	//	TESTS FOR LAB 6 (COMMAND) BEGIN HERE
	
	
	Environment e = Environment.getWorldInstance(9, 9);
	MockLifeForm m = new MockLifeForm("bob", 10);
	
	//	set up the Environment for all tests
	@Before
	public void before()
	{
		Pistol p = new Pistol();
		e.addWeapon(p, 4, 4);
		e.addLifeForm(m, 4, 4);
	}
	
	//	clean up the Environment after all tests
	@After
	public void cleanup()
	{
		e.clearEnvironment();
	}
	
	//	the LifeForm acquires the Weapon from the Cell
	@Test
	public void testWeaponAcquired()
	{
		Weapon p = e.getWeapon(1, 4, 4);
		Command acquire = new Acquire(e);
		acquire.execute(4, 4);
		assertEquals(p, m.getWeapon());
	}
	
	//	the LifeForm doesn't acquire a Weapon if there is non in the Cell
	@Test
	public void testNoAcquire()
	{
		e.removeWeapon(1, 4, 4);
		Command acquire = new Acquire(e);
		acquire.execute(4, 4);
		assertNull(m.getWeapon());
		assertNull(e.getWeapon(1, 4, 4));
		assertNull(e.getWeapon(2, 4, 4));
	}
	
	//	if the LifeForm is holding a Weapon, it will swap with the one in the Cell.
	@Test
	public void testWeaponSwapped()
	{
		Weapon p = e.getWeapon(1, 4, 4);	//	the Weapon in the Environment
		Weapon wep = new Pistol();			//	the LifeForm's Weapon
		m.setWeapon(wep);
		Command acquire = new Acquire(e);
		acquire.execute(4, 4);
		assertEquals(p, m.getWeapon());
		assertEquals(wep, e.getWeapon(1, 4, 4));
	}
	
	
	//	BONUS TESTS FOR LAB 6 (COMMAND) BEGIN HERE
	
	
	//	if there is no Weapon in slot 1, it will acquire the Weapon from slot 2.
	@Test
	public void testAcquireWeapon2()
	{
		Weapon p = e.getWeapon(1, 4, 4);
		e.addWeapon(p, 4, 4);		//	duplicate the Weapon from slot 1 into slot 2
		e.removeWeapon(1, 4, 4);	//	then remove the one from slot 1.
		Command acquire = new Acquire(e);
		acquire.execute(4, 4);
		assertEquals(p, m.getWeapon());
	}
	
	/**
	 * Put a weapon in both slots in the Environment to test user dialog,
	 * allowing them to select which weapon to pick up.
	 * @author Josh Varone
	 */
	@Test
	public void testUserSelectWeapon()
	{
		Weapon p = e.getWeapon(1, 4, 4);
		ChainGun cg = new ChainGun();
		e.addWeapon(cg, 4, 4);
		Command acquire = new Acquire(e);
		JOptionPane.showMessageDialog(null, "Please select the Pistol in the next dialog box.");
		acquire.execute(4, 4);
		assertEquals(p, m.getWeapon());
	}
}
