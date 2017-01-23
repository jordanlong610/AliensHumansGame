package lifeform;
import static org.junit.Assert.*;
import environment.Environment;
import exceptions.*;
import gameplay.SimpleTimer;
import org.junit.Before;
import org.junit.Test;
import weapon.*;

/**
 * Tests the functionality provided by the LifeForm class
 * @author David Reichard
 * @author Josh Varone - Decorator, Singleton, and Command tests
 *
 */
public class TestLifeForm
{
	// START tests for Command Lab
	
	/**
	 * LifeForm should know its name, lifepoints, direction, and maxSpeed.
	 * @author Josh Varone
	 */
	@Test
	public void testInitialization()
	{
		LifeForm entity = new MockLifeForm("Entity", 50);
		assertEquals("Entity", entity.getName());
		assertEquals(50, entity.getCurrentLifePoints());
		//Test that LifeForm initial direction is North
		assertEquals("North", entity.getDirection());
		//While abstract LifeForm has maxSpeed 0, MockLifeForm has maxSpeed 3
		assertEquals(3, entity.getMaxSpeed());
	}
	
	/**
	 * LifeForm should begin facing North, but can be changed to valid options.
	 * Invalid options should not work.
	 * @author Josh Varone
	 */
	@Test
	public void testChangeDirection()
	{
		LifeForm entity = new MockLifeForm("Entity", 50);
		assertEquals("North", entity.getDirection());
		//Direction should successfully update to East
		assertTrue(entity.changeDirection("East"));
		assertEquals("East", entity.getDirection());
		//Invalid direction change should fail
		assertFalse(entity.changeDirection("Wset"));
		assertEquals("East", entity.getDirection());
		//Direction should change regardless of capitalization
		assertTrue(entity.changeDirection("west"));
		assertEquals("West", entity.getDirection());
		//Direction should successfully update to South
		assertTrue(entity.changeDirection("south"));
		assertEquals("South", entity.getDirection());
	}
	
	/*
	 * START Decorator Lab tests
	 *********************************************/
	
	/**
	 * Clear all cells in the Environment before each test is run
	 * to ensure there are no remaining LifeForms or weapons in either.
	 * @author Josh Varone
	 */
	@Before
	public void clearEnvironment()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		e.clearEnvironment();
	}
	
	/**
	 * Test that a LifeForm can pick up and retain a weapon.
	 * @author Joshua Varone
	 */
	@Test
	public void testPickUpWeapon()
	{
		LifeForm entity = new MockLifeForm("Al", 30);
		Weapon weapon = new Pistol();
		entity.setWeapon(weapon);
		//entity should now be holding weapon
		assertEquals(weapon, entity.getWeapon());
	}
	
	/**
	 * Test that a LifeForm can only hold one weapon, and
	 * cannot pick up a new one if already holding one.
	 * @author Joshua Varone
	 */
	@Test
	public void testOnlyHoldOneWeapon()
	{
		LifeForm entity = new MockLifeForm("Al", 30);
		Weapon weapon = new Pistol();
		Weapon weapon1 = new PlasmaCannon();
		entity.setWeapon(weapon);
		assertEquals(weapon, entity.getWeapon());
		entity.setWeapon(weapon1);
		assertEquals(weapon, entity.getWeapon());
	}
	
	/**
	 * Test that a LifeForm can drop its current weapon.
	 * @author Joshua Varone
	 */
	@Test
	public void testDropWeapon()
	{
		LifeForm entity = new MockLifeForm("Al", 30);
		Weapon weapon = new Pistol();
		entity.setWeapon(weapon);
		entity.setWeapon(null);
		assertNull(entity.getWeapon());
	}	
	
	/**
	 * Test that a LifeForm inflicts the correct damage when using a weapon.
	 * @author Joshua Varone
	 */
	@Test
	public void testAttackWithWeapon() throws EnvironmentException
	{
		LifeForm attacker = new MockLifeForm("Al", 30);
		LifeForm opponent = new MockLifeForm("Bo", 40);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(attacker, 0, 0);
		e.addLifeForm(opponent, 2, 0);
		Weapon weapon = new Pistol();
		attacker.setWeapon(weapon);
		//weapon ammo should start at max
		assertEquals(weapon.getMaxAmmo(), weapon.getCurrentAmmo());
		//weapon damage should be 8
		assertEquals(8, weapon.getDamage(e.getDistance(attacker, opponent)));
		attacker.attack(opponent);
		//opponent should lose 8 life points
		assertEquals(32, opponent.getCurrentLifePoints());
		//ammo should decrement
		assertEquals(weapon.getMaxAmmo()-1, weapon.getCurrentAmmo());
	}
	
	/**
	 * Test that a LifeForm uses its attack strength if its weapon is
	 * out of ammo.
	 * @author Joshua Varone
	 * @throws OutOfAmmoException 
	 */
	@Test
	public void testAttackWhenOutOfAmmo() throws OutOfAmmoException
	{
		LifeForm attacker = new MockLifeForm("Al", 30);
		LifeForm opponent = new MockLifeForm("Bo", 40);
		attacker.setAttackStrength(11);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(attacker, 0, 0);
		e.addLifeForm(opponent, 0, 1);
		SimpleTimer timer = new SimpleTimer();
		Weapon weapon = new PlasmaCannon();
		timer.addTimeObserver(weapon);
		attacker.setWeapon(weapon);
		weapon.fire();
		timer.timeChanged();
		weapon.fire();
		timer.timeChanged();
		weapon.fire();
		timer.timeChanged();
		weapon.fire();
		//ammo should be 0
		assertEquals(0, weapon.getCurrentAmmo());
		attacker.attack(opponent);
		//opponent should lose 11 life points
		assertEquals(29, opponent.getCurrentLifePoints());
	}
	
	/**
	 * Test that without a weapon, LifeForm uses its attack strength,
	 * and only inflicts damage if within 5 feet of its target.
	 * @author Joshua Varone
	 */
	@Test
	public void testAttackWithoutWeapon()
	{
		LifeForm attacker = new MockLifeForm("Al", 30);
		LifeForm opponent = new MockLifeForm("Bo", 40);
		attacker.setAttackStrength(7);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(attacker, 0, 0);
		e.addLifeForm(opponent, 1, 0);
		attacker.attack(opponent);
		//within 5 feet, attacker should inflict damage
		assertEquals(33, opponent.getCurrentLifePoints());
		e.removeLifeForm(1, 0);
		e.addLifeForm(opponent, 2, 0);
		//at distance greater than 5 feet, no damage should result
		attacker.attack(opponent);
		assertEquals(33, opponent.getCurrentLifePoints());
	}
	
	/**
	 * Test that a LifeForm can reload a weapon.
	 * @author Joshua Varone
	 */
	@Test
	public void testReloadWeapon() throws OutOfAmmoException
	{
		LifeForm entity = new MockLifeForm("Al", 30);
		Weapon weapon = new Pistol();
		entity.setWeapon(weapon);
		entity.getWeapon().fire();
		assertEquals(entity.getWeapon().getMaxAmmo()-1, entity.getWeapon().getCurrentAmmo());
		entity.getWeapon().reload();
		assertEquals(entity.getWeapon().getMaxAmmo(), entity.getWeapon().getCurrentAmmo());
	}
	
	/******
	 * EXTRA CREDIT tests for Decorator Pattern
	 ******/
	
	/**
	 * Test that a LifeForm inflicts the correct damage with different weapons.
	 * @author Joshua Varone
	 */
	@Test
	public void testAttackDifferentWeapons()
	{
		LifeForm attacker = new MockLifeForm("Al", 30);
		LifeForm opponent = new MockLifeForm("Bo", 100);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(attacker, 0, 0);
		e.addLifeForm(opponent, 2, 0);
		Weapon pistol = new Pistol();
		Weapon plasmaCannon = new PlasmaCannon();
		attacker.setWeapon(pistol);
		attacker.attack(opponent);
		//opponent should lose 8 life points and ammo should decrement
		assertEquals(92, opponent.getCurrentLifePoints());
		assertEquals(pistol.getMaxAmmo()-1, pistol.getCurrentAmmo());
		//switch weapon to plasmaCannon
		attacker.setWeapon(null);
		attacker.setWeapon(plasmaCannon);
		attacker.attack(opponent);
		//opponent should lose 50 life points and ammo should decrement
		assertEquals(42, opponent.getCurrentLifePoints());
		assertEquals(plasmaCannon.getMaxAmmo()-1, plasmaCannon.getCurrentAmmo());
	}
	
	/**
	 * Test that a LifeForm inflicts the correct damage based on proximity.
	 * @author Joshua Varone
	 */
	@Test
	public void testAttackDifferentDistances()
	{
		LifeForm attacker = new MockLifeForm("Al", 30);
		LifeForm opponent = new MockLifeForm("Bo", 40);
		Weapon weapon = new Pistol();
		attacker.setWeapon(weapon);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(attacker, 0, 0);
		e.addLifeForm(opponent, 0, 2);
		attacker.attack(opponent);
		//opponent should lose 8 life points
		assertEquals(32, opponent.getCurrentLifePoints());
		e.removeLifeForm(0, 2);
		e.addLifeForm(opponent, 0, 4);
		attacker.attack(opponent);
		//opponent should lose 4 life points
		assertEquals(28, opponent.getCurrentLifePoints());
	}
	
	/*
	 * Start Section for Observer Pattern Tests.
	 ***********************************************************************/
	/**
	 * Test that attack strength of LifeForm may be set and later retrieved.
	 */
	@Test
	public void testSetAttackStrength()
	{
		MockLifeForm entity = new MockLifeForm("Bob", 40);
		entity.setAttackStrength(10);
		assertEquals(10, entity.getAttackStrength());
	}
	
	/**
	 * Test that a LifeForm can attack another LifeForm.
	 */
	@Test
	public void testAttackLifeform()
	{
		MockLifeForm bob = new MockLifeForm("Bob", 40);
		MockLifeForm fred = new MockLifeForm("Fred", 40);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(bob, 0, 0);
		e.addLifeForm(fred, 1, 0);
		bob.setAttackStrength(10);
		bob.attack(fred);
		assertEquals(30, fred.getCurrentLifePoints());
	}
	
	/**
	 * Test that a life form with 0 health (dead) is unable to attack another life form.
	 */
	@Test
	public void testAttackWhenDead()
	{
		MockLifeForm bob = new MockLifeForm("Bob", 0);
		MockLifeForm fred = new MockLifeForm("Fred", 40);
		bob.setAttackStrength(10);
		bob.attack(fred);
		assertEquals(40, fred.getCurrentLifePoints());
	}
	
	/**
	 * Test that a life form can observe the passage of time.
	 */
	@Test
	public void testTrackTime()
	{
		MockLifeForm bob = new MockLifeForm("Bob", 40);
		SimpleTimer timer = new SimpleTimer();
		timer.addTimeObserver(bob);
		assertEquals(0, bob.getRound());
		timer.timeChanged();
		assertEquals(1, bob.getRound());
	}
	
	
	/*
	 * Start Section for Strategy Pattern Tests.
	 ************************************************************************/
	/**
	 * Test taking damage. Hit points should never drop below 0.
	 */
	@Test
	public void testTakeHit()
	{
		MockLifeForm entity = new MockLifeForm("Bob", 40);
		entity.takeHit(5); // drop hit points from 40 to 35
		assertEquals(35, entity.getCurrentLifePoints());
		entity.takeHit(35); // drop it the rest of the way to 0
		assertEquals(0, entity.getCurrentLifePoints());
		entity.takeHit(1); // hit points shouldn't drop below 0
		assertEquals(0, entity.getCurrentLifePoints());
	}
}
