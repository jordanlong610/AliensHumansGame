package recovery;

/**
 * Recovery behavior that implements a fractional recovery of current hit points.
 * For example: If instantiated at 10% recovery (0.1), and current hit points are
 * equal to 100, then it would recover 10 hit points (as long as this does not go
 * over max hit points)
 * @author David Reichard
 *
 */
public class RecoveryFractional implements RecoveryBehavior
{
	private double percentRecovery;
	
	/**
	 * Initialize fractional recovery behavior.
	 * @param percent Percent of recovery based from current hit points.
	 */
	public RecoveryFractional(double percent)
	{
		percentRecovery = percent;
	}

	/**
	 * Calculate recovery based off fraction of current life points.
	 * @return Final hit points after recovery.
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife)
	{
		int result = (int)Math.ceil(currentLife + currentLife * percentRecovery);
		if (result > maxLife)
			return maxLife;
		return result;
	}
	
}
