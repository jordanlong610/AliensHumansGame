package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test fractional recovery behavior. This is based upon 
 * some fraction of the current life points.
 * @author David Reichard
 */
public class TestRecoveryFractional 
{	
	/**
	 * Test that we can not recover above maximum hit points.
	 * (when currentLP = maxLP)
	 */
	@Test
	public void testMaximumRecovery()
	{
		RecoveryFractional r1 = new RecoveryFractional(0.1);
		assertEquals(100, r1.calculateRecovery(100,100));
	}
	
	/**
	 * Test complete recovery w/o going over.
	 */
	@Test
	public void testCompleteRecovery()
	{
		RecoveryFractional r1 = new RecoveryFractional(0.2);
		assertEquals(110, r1.calculateRecovery(100, 110));
	}
	
	/**
	 * Test recovering a full step.
	 */
	@Test
	public void testFullStep()
	{
		RecoveryFractional r1 = new RecoveryFractional(0.1);
		assertEquals(99, r1.calculateRecovery(90, 110));
	}
	
	/**
	 * Test that we can not recover from death. (When LP = 0)
	 */
	@Test
	public void testWhenDead()
	{
		RecoveryFractional r1 = new RecoveryFractional(0.1);
		assertEquals(0, r1.calculateRecovery(0, 100));
	}
	
	/**
	 * Test that we are rounding up on a fractional recovery.
	 * Per lab text, 10% of 93 life points should round up to 10
	 * total points of recovery, instead of 9.
	 */
	@Test
	public void testRoundUp()
	{
		RecoveryFractional r1 = new RecoveryFractional(0.1);
		assertEquals(103, r1.calculateRecovery(93, 110));
	}
}
