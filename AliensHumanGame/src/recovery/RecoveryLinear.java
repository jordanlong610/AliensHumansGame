package recovery;

/**
 * A linear recovery behavior, this recovers a fixed amount
 * of hit points every recovery cycle.
 * @author David Reichard
 *
 */
public class RecoveryLinear implements RecoveryBehavior
{
	private int step;
	
	/**
	 * Initialize the recovery behavior.
	 * @param step Amount of life points to recovery each cycle.
	 */
	public RecoveryLinear(int step)
	{
		this.step = step;
	}
	
	/**
	 * Recovery is linear, and should not raise above max life.
	 * @return Final amount of hit points after recovery.
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife) 
	{
		if (currentLife <= 0)
			return 0;
		int result = currentLife + step;
		if (result > maxLife)
			result = maxLife;
		return result;
	}

}
