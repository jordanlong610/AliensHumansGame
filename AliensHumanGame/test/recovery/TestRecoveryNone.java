package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the functionality of the RecoveryNone behavior.
 * @author David Reichard
 *
 */
public class TestRecoveryNone
{
	/**
	 * Test that recovery is zero when hit points already
	 * equal max hit points.
	 */
	@Test
	public void testRecoveryAtMax()
	{
		RecoveryNone behavior = new RecoveryNone();
		assertEquals(40, behavior.calculateRecovery(40, 40));
	}
	
	/**
	 * Test that recovery does not occur when hurt.
	 */
	@Test
	public void testRecoveryWhenHurt()
	{
		RecoveryNone behavior = new RecoveryNone();
		assertEquals(35, behavior.calculateRecovery(35, 40));
	}
}
