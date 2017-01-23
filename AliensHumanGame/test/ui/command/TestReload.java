package ui.command;
import static org.junit.Assert.*;
import lifeform.MockLifeForm;

import org.junit.Test;

import environment.Environment;
import exceptions.OutOfAmmoException;
import ui.command.Command;
import ui.command.Reload;
import weapon.Pistol;

/**
 * Tests the functionality of the Reload class
 * @author Jeff Titanich
 */
public class TestReload
{
	
	
	//	There are no Reload tests on the rubric so
	//	BONUS TESTS FOR LAB 6 (COMMAND) BEGIN HERE
	
	
	//	Reload execute command works correctly
	@Test
	public void testReload() throws OutOfAmmoException
	{
		Environment e;
		e = Environment.getWorldInstance();
		e.clearEnvironment();
		e = Environment.getWorldInstance(2, 2);
		Command reload = new Reload(e);
		MockLifeForm m = new MockLifeForm("bob", 10);
		Pistol p = new Pistol();
		m.setWeapon(p);
		e.addLifeForm(m, 0, 0);
		
		p.fire();
		p.fire();
		assertEquals(8, p.getCurrentAmmo());
		
		reload.execute(0, 0);
		assertEquals(10, p.getCurrentAmmo());
		
		e.clearEnvironment();
	}
}
