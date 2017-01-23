package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the linear recovery behavior.
 * @author David Reichard
 *
 */
public class TestRecoveryLinear 
{
	/**
	 * There should be no recovery when no damage has been done.
	 */
	@Test
	public void testNoRecoveryWhenNotHurt()
	{
		RecoveryLinear r1 = new RecoveryLinear(3);
		int maxLifePts = 30;
		int result = r1.calculateRecovery(maxLifePts, maxLifePts);
		assertEquals(maxLifePts, result);
	}
	
	/**
	 * Test a large amount of recovery. Make sure we don't
	 * go above the max value. (recover to max LP w/o going over)
	 */
	@Test
	public void testLargeRecovery()
	{
		RecoveryLinear r1 = new RecoveryLinear(30);
		assertEquals(30, r1.calculateRecovery(20, 30));
	}
	
	/**
	 * Test a small amount of recovery. (Full Step)
	 */
	@Test
	public void testSmallRecovery()
	{
		RecoveryLinear r1 = new RecoveryLinear(3);
		assertEquals(23, r1.calculateRecovery(20, 30));
	}
	
	/**
	 * No recovery should happen when dead (hit points at 0).
	 */
	@Test
	public void testWhenDead()
	{
		RecoveryLinear r1 = new RecoveryLinear(30);
		assertEquals(0, r1.calculateRecovery(0, 30));
	}
	
	/**
	 * Test complete recovery.
	 */
	@Test
	public void testCompleteRecovery()
	{
		RecoveryLinear r1 = new RecoveryLinear(29);
		assertEquals(30, r1.calculateRecovery(1, 30));
	}
}
