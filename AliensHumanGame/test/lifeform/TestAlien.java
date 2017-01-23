package lifeform;

import static org.junit.Assert.*;
import gameplay.SimpleTimer;

import org.junit.Test;

import recovery.RecoveryLinear;
import environment.Environment;
import exceptions.InvalidRateOfRecoveryException;

/**
 * Test the Alien concrete class.
 * @author David Reichard
 * @author Josh Varone - updated for Command Lab
 *
 */
public class TestAlien
{
	/**
	 * START tests for Command Lab
	 */
	
	/**
	 * Test initialization of Aliens.
	 * @author Josh Varone
	 */
	@Test
	public void testInitialization() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		assertEquals("Al", alien.getName());
		assertEquals(50, alien.getCurrentLifePoints());
		//Test that Alien maxSpeed is 2
		assertEquals(2, alien.getMaxSpeed());
		assertEquals("North", alien.getDirection());		
	}
	
	/*
	 * Start Section for Observer Pattern Tests.
	 ***********************************************************************/
	/**
	 * Test that Alien has a default attack strength of 10.
	 */
	@Test
	public void testDefaultAttackStrength() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50);
		assertEquals(10, roger.getAttackStrength());
	}
	
	/**
	 * Test setting of recovery rate through constructor and setter method.
	 * Test getting of recovery rate through getter method.
	 */
	@Test
	public void testGetAndSetRecoveryRate() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), 2);
		assertEquals(2, roger.getRecoveryRate());
		roger.setRecoveryRate(1);
		assertEquals(1, roger.getRecoveryRate());
	}
	
	/**
	 * Test that we get an exception with a negative recovery rate during construction.
	 */
	@Test(expected = InvalidRateOfRecoveryException.class)
	public void testNegativeRecoveryInitialization() throws InvalidRateOfRecoveryException
	{
		@SuppressWarnings("unused")
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), -1);
	}
	
	/**
	 * And also test that we get an exception after setting a negative recovery rate after construction.
	 */
	@Test(expected = InvalidRateOfRecoveryException.class)
	public void testNegativeRecoverySet() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), 2);
		roger.setRecoveryRate(-1);
	}
	
	/**
	 * Test recovery rate of 0 (Alien should never heal). 
	 */
	@Test
	public void testRecoveryZero() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), 0);
		SimpleTimer timer = new SimpleTimer();
		timer.addTimeObserver(roger);
		roger.takeHit(30);
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(20, roger.getCurrentLifePoints());
	}
	
	/**
	 * Test recovery rate of 2 (Alien should heal every 2 rounds).
	 */
	@Test
	public void testRecoveryTwo() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), 2);
		SimpleTimer timer = new SimpleTimer();
		timer.addTimeObserver(roger);
		roger.takeHit(30);
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(30, roger.getCurrentLifePoints());
	}
	
	/**
	 * Test a recovery rate of 3 (Alien should heal every 3 rounds).
	 */
	@Test
	public void testRecoveryThree() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), 3);
		SimpleTimer timer = new SimpleTimer();
		timer.addTimeObserver(roger);
		roger.takeHit(30);
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(30, roger.getCurrentLifePoints());
	}
	
	/**
	 * Test that no recover occurs when alien is removed
	 * from the timer as an observer.
	 */
	@Test
	public void testRemoveObserver() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), 2);
		SimpleTimer timer = new SimpleTimer();
		timer.addTimeObserver(roger);
		roger.takeHit(30);
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		timer.timeChanged();
		assertEquals(30, roger.getCurrentLifePoints());
		// now remove the alien as an observer and it should not heal
		timer.removeTimeObserver(roger);
		timer.timeChanged();
		timer.timeChanged();
		assertEquals(30, roger.getCurrentLifePoints());
		
	}
	
	//////////////////////////////////////////////////
	// BONUS TESTS NOT INCLUDED IN RUBRIC FOR LAB 3 //
	//////////////////////////////////////////////////
	/**
	 * Test that Alien takes damage properly using new attack method.
	 */
	@Test
	public void testTakeDamageProperly() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50);
		MockLifeForm stan = new MockLifeForm("Stan", 5000);
		Environment e = Environment.getWorldInstance(5, 5);
		e.addLifeForm(roger, 0, 0);
		e.addLifeForm(stan, 1, 0);
		stan.setAttackStrength(20);
		assertEquals(50, roger.getCurrentLifePoints());
		stan.attack(roger);
	}
	
	/**
	 * Test border cases for recovery. If we recover at 1 round, and then
	 * set the recovery rate to 2 rounds, it should take an additional
	 * 2 rounds to recover again and not recovery right away on the second
	 * round.
	 */
	@Test
	public void testRecoveryBorder() throws InvalidRateOfRecoveryException
	{
		Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), 1);
		SimpleTimer timer = new SimpleTimer();
		timer.addTimeObserver(roger);
		roger.takeHit(30);
		assertEquals(20, roger.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(30, roger.getCurrentLifePoints());
		roger.setRecoveryRate(2);
		timer.timeChanged(); // we shouldn't have recovered yet
		assertEquals(30, roger.getCurrentLifePoints());
	}
	
	/*
	 * Start Section for Strategy Pattern Tests.
	 ***********************************************************************/
	/**
	 * Test recovery of Alien.
	 */
	@Test
	public void testRecovery() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Roger", 30, new RecoveryLinear(5), 2);
		alien.takeHit(10);
		alien.recover();
		assertEquals(25, alien.getCurrentLifePoints());
	}
}
