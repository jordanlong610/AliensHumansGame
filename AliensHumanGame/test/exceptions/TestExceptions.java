package exceptions;

import static org.junit.Assert.*;
import lifeform.Alien;

import org.junit.Test;

import recovery.RecoveryLinear;

/**
 * Test for certain internal aspects of exceptions.
 * @author David Reichard
 */
public class TestExceptions 
{
	// BONUS TEST
	/**
	 * Test that we can retrieve the invalid rate to display to user.
	 */
	@Test
	public void testInvalidRateOfRecoveryException()
	{
		try 
		{
			@SuppressWarnings("unused")
			Alien roger = new Alien("Roger", 50, new RecoveryLinear(10), -3);
		} 
		catch (InvalidRateOfRecoveryException e) 
		{
			assertEquals(-3, e.getRate());
		}
	}

}
