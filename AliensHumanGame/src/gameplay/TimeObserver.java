package gameplay;

/**
 * TimeObserver interface for observers of objects implementing the Timer interface.
 * @author David Reichard
 *
 */
public interface TimeObserver 
{
	/**
	 * Should be called by a Timer on each clock cycle  if the observer is
	 * registered and pass in current time(or round) depending on implementation.
	 * @param time Time (or round) passed in depending on implementation.
	 */
	public void updateTime(int time);
}
