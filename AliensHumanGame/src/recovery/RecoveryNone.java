package recovery;

/**
 * Recovery behavior that implements no recovery.
 * @author David Reichard
 *
 */
public class RecoveryNone implements RecoveryBehavior {

	/**
	 * @return Calculated hit point value after recovery, in this case no recovery.
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife)
	{
		return currentLife;
	}

}