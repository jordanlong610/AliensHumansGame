package lifeform;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.InvalidRateOfRecoveryException;

/**
 * Test the Human concrete class.
 * @author David Reichard
 * @author Josh Varone - updated for Command Lab
 *
 */
public class TestHuman 
{
	/**
	 * START tests for Command Lab
	 */
	
	/**
	 * Test initialization of humans.
	 * @author Josh Varone
	 */
	@Test
	public void testInitialization()
	{
		Human human = new Human("Bo", 50, 10);
		assertEquals("Bo", human.getName());
		assertEquals(50, human.getCurrentLifePoints());
		//Test that Human maxSpeed is 3
		assertEquals(3, human.getMaxSpeed());
		assertEquals("North", human.getDirection());
	}
	
	/*
	 * Start Section for Observer Pattern Tests.
	 ***********************************************************************/
	/**
	 * Test that humans have a default attack strength of 5.
	 */
	@Test
	public void testDefaultAttackStrength()
	{
		Human entity = new Human("Fred", 40);
		assertEquals(5, entity.getAttackStrength());
	}
	
	/**
	 * Test that humans take damage normally without armor.
	 */
	@Test
	public void testTakeDamageWithoutArmor()
	{
		Human entity = new Human("Fred", 40, 0);
		MockLifeForm attacker = new MockLifeForm("Piglet", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(entity, 0, 0);
		e.addLifeForm(attacker, 1, 0);
		attacker.setAttackStrength(20);
		assertEquals(40, entity.getCurrentLifePoints());
		attacker.attack(entity);
	}
	
	/**
	 * Test that humans can absorb damage based on their armor value.
	 * Test border cases where damage should be completely absorbed, to when
	 * some damage is given. Also make sure we don't "generate" hit points when
	 * less damage is given than armor points.
	 */
	@Test
	public void testTakeDamageWithArmor() throws InvalidRateOfRecoveryException
	{
		Human stan = new Human("Stan", 50, 20);
		Alien roger = new Alien("Roger", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(stan, 0, 0);
		e.addLifeForm(roger, 0, 1);
		// armor reduces damage when less than attack
		roger.setAttackStrength(21);
		roger.attack(stan);
		assertEquals(49, stan.getCurrentLifePoints());
		
		// armor absorbs all damage when armor is greater than attack
		roger.setAttackStrength(5);
		roger.attack(stan);
		assertEquals(49, stan.getCurrentLifePoints());
		
		// armor absorbs all damage when armor = attack
		roger.setAttackStrength(20);
		roger.attack(stan);
		assertEquals(49, stan.getCurrentLifePoints());
	}
	
	
	/*
	 * Start Section for Strategy Pattern Tests.
	 ***********************************************************************/
	/**
	 * Test setting and getting armor points.
	 */
	@Test
	public void testSetGetArmorPoints()
	{
		Human entity = new Human("Fred", 40, 10);
		entity.setArmorPoints(15);
		assertEquals(15, entity.getArmorPoints());
	}
	
	/**
	 * Test that armor points may not drop below zero when
	 * being set or during initialization.
	 */
	@Test
	public void testArmorNotBelowZero()
	{
		Human entity = new Human("Fred", 40, 10);
		entity.setArmorPoints(-1); 
		// armor should not fall below 0
		assertEquals(0, entity.getArmorPoints());
		entity = new Human("Fred", 40, -1);
		// armor should not be allowed to be initialized below 0 either
		assertEquals(0, entity.getArmorPoints());
	}
}
