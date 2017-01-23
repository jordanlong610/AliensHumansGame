package gameplay;

/**
 * Timer interface used for objects which send updates to observers
 * upon any time  changes.
 * @author David Reichard
 *
 */
public interface Timer 
{
	/**
	 * Should be used for adding a time observer to the Timer..
	 * @param observer observer to be registered for receiving timer updates
	 */
	public void addTimeObserver(TimeObserver observer);
	
	/**
	 * Should be used to remove a time observer from the Timer.
	 * @param observer observer to be removed from receiving timer updates.
	 */
	public void removeTimeObserver(TimeObserver observer);
	
	/**
	 * Should be called on each timer cycle and update all observers.
	 */
	public void timeChanged();
}
