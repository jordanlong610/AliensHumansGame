package ui.command;
import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lifeform.MockLifeForm;
import ui.command.UserInterFace;
import weapon.Pistol;
import weapon.Weapon;
import environment.Environment;

/**
 * Tests for UserInterFace Commands
 * @author Jordan Long
 */

public class TestUserInterfaceCommand 
{

	Environment e = Environment.getWorldInstance(5, 5);
	MockLifeForm m = new MockLifeForm("bob", 10);
	
	//	set up the Environment for all tests
	@Before
	public void before()
	{
		e.addLifeForm(m, 0, 0);
		Pistol p = new Pistol();
		m.setWeapon(p);
	}
	
	//	clean up the Environment after all tests
	@After
	public void cleanup()
	{
		e.clearEnvironment();
	}
	
	
	
	/**
	 * Test turnNorth button push.
	 * When pressed, current player position will be changed to North, if already North it will stay the same.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testTurnPlayerNorth() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		
		m.changeDirection("South");					//Changes initial player position from North to South.
		assertEquals("South", m.getDirection());

		UserInterFace.turnNorth.doClick();			//Button click, will change player position to North
		
		assertEquals("North", m.getDirection());	//Checks that player position is now north.
		
		UserInterFace.turnNorth.doClick();			//Checking that when player is north, it will stay north.
		assertEquals("North", m.getDirection());	//Checks that player position is now north.
	}
	
	/**
	 * Test turnEast button push.
	 * When pressed, current player position will be changed to East, if already East it will stay the same.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testTurnPlayerEast() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		
		assertEquals("North", m.getDirection());

		UserInterFace.turnEast.doClick();			//Button click, will change player position to North
		
		assertEquals("East", m.getDirection());	//Checks that player position is now north.
		
		UserInterFace.turnEast.doClick();			//Checking that when player is north, it will stay north.
		assertEquals("East", m.getDirection());	//Checks that player position is now north.
	}
	
	
	/**
	 * Test turnSouth button push.
	 * When pressed, current player position will be changed to South, if already South it will stay the same.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testTurnPlayerSouth() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		
		assertEquals("North", m.getDirection());

		UserInterFace.turnSouth.doClick();			//Button click, will change player position to North
		
		assertEquals("South", m.getDirection());	//Checks that player position is now north.
		
		UserInterFace.turnSouth.doClick();			//Checking that when player is north, it will stay north.
		assertEquals("South", m.getDirection());	//Checks that player position is now north.
	}
	
	
	
	/**
	 * Test turnWest button push.
	 * When pressed, current player position will be changed to West, if already West it will stay the same.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testTurnPlayerWest() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		
		assertEquals("North", m.getDirection());

		UserInterFace.turnWest.doClick();			//Button click, will change player position to North
		
		assertEquals("West", m.getDirection());	//Checks that player position is now north.
		
		UserInterFace.turnWest.doClick();			//Checking that when player is north, it will stay north.
		assertEquals("West", m.getDirection());	//Checks that player position is now north.
	}
	
	
	/**
	 * Test acquire button push.
	 * Picks up weapon from cell that LifeForm is in, if available.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testAcquireButton() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		m.setWeapon(null);//removes pre-loaded weapon from LifeForm
		
		Weapon wp1 = new Pistol();
		//there should not be weapons in the cell
		assertNull(e.getWeapon(1, 0, 0));
		//two weapons should add successfully
		assertTrue(e.addWeapon(wp1, 0, 0));
		assertEquals(wp1, e.getWeapon(1, 0, 0));
		
		UserInterFace.acquire.doClick();	//Acquire command, picks up weapon from cell.
		
		assertEquals(wp1, m.getWeapon());
	}
	
	
	/**
	 * Test drop button push.
	 * Drops weapon into cell.
	 * Will not drop into cell if 2 weapons are already present in cell.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testDropButton() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		Weapon p = m.getWeapon();		//Temp copy of Pistol that is being held by LF.
		
		assertNull(e.getWeapon(1,0,0));	//Checks environment to see if any weapons present.
		
		UserInterFace.drop.doClick();	//Drop command, drops LF weapon into cell.
		
		assertNull(m.getWeapon());		//Checks to see if LF dropped weapon.
		assertEquals(p,e.getWeapon(1, 0, 0));
	}
	
	

	
	/**
	 * Test Move button push.
	 * Moves player when pressed. A dialog box will come up asking how many spaces to move the player.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testMoveButton() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		m.changeDirection("South");					//Change LF Direction to south.
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		UserInterFace.move.doClick();	//move command, select 3 spaces!
		
		assertEquals(m, e.getLifeForm(3,0));
	}
	
	
	
	
	/**
	 * Test attack button push.
	 * LifeForm is at (0,0), facing south, attacks enemy 4 spaces away.
	 * Checking that pistol has fired when button is pressed, and enemy has received damage.
	 * Should receive full 10 pts of damage from pistol.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testAttackButton() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		
		e.getLifeForm(0, 0).changeDirection("South");
		
		MockLifeForm enemy = new MockLifeForm("enemy", 15);
		e.addLifeForm(enemy, 1, 0);
		
		UserInterFace.attack.doClick();
		
		assertEquals(9, m.getWeapon().getCurrentAmmo());
		assertEquals(5, enemy.getCurrentLifePoints());
	}
	
	


	/**
	 * Test reload button push.
	 * When pressed, will reload current weapon LF is holding. If weapon at max ammo, ammo stays the same.
	 * @throws Exception
	 * @author Jordan Long
	 */ 
	@Test
	public void testReloadButton() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		InvokerBuilder IB = new InvokerBuilder();
		Weapon p = m.getWeapon();		//Temporary copy of Pistol that is being held by LF.
		
		p.fire();
		p.fire();
		assertEquals(8, p.getCurrentAmmo());
		
		UserInterFace.reload.doClick();	//Drop command, drops LF weapon into cell.
		

		assertEquals(10, p.getCurrentAmmo());
	}
	
}
