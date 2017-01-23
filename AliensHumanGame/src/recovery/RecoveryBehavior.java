package recovery;

/**
 * Public interface for a recovery behavior.
 * @author David Reichard
 *
 */
public interface RecoveryBehavior 
{
	public int calculateRecovery(int currentLife, int maxLife);
}
