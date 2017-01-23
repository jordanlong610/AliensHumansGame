package gameplay;

import java.util.ArrayList;

/**
 * Simple Timer class implementing the Timer behavior.
 * This class is instantiated with a delay (default of 1000 milliseconds) and
 * will 'tick' after time equaling the delay has passed. Upon each tick the
 * updateTime() method of each of its observers is called and passed the current
 * round, which increments upon each completed cycle.
 * @author David Reichard
 *
 */
public class SimpleTimer extends Thread implements Timer
{
	private int round;
	private ArrayList<TimeObserver> theObservers;
	private int delay;
	private boolean running = false;
	
	/**
	 * Instantiate SimpleTimer with default delay of 1000ms.
	 */
	public SimpleTimer()
	{
		this(1000);
	}
	
	/**
	 * Instantiate SimpleTimer with a specific delay in milliseconds.
	 * @param delay Amount of time that should pass before sending new update.
	 */
	public SimpleTimer(int delay)
	{
		super();
		theObservers = new ArrayList<TimeObserver>();
		this.delay = delay;
	}
	
	/**
	 * @return total number of objects receiving time updates.
	 */
	public int getObserverCount()
	{
		return theObservers.size();
	}
	
	/**
	 * Get the current round of the SimpleTimer.
	 * @return current round
	 */
	public int getRound()
	{
		return round;
	}
	
	/**
	 * Add an observer which should receive updates on each cycle.
	 */
	@Override
	public void addTimeObserver(TimeObserver observer) 
	{
		theObservers.add(observer);
	}

	/**
	 * Remove an observer which should no longer receive updates each cycle.
	 */
	@Override
	public void removeTimeObserver(TimeObserver observer) 
	{
		theObservers.remove(observer);		
	}

	/**
	 * Called on each tick cycle, increments round and updates all registered
	 * observers with current round information.
	 */
	@Override
	public void timeChanged() 
	{
		round++;
		for (TimeObserver o: theObservers)
		{
			o.updateTime(round);
		}
	}
	
	/**
	 * Call timeChanged() after each tick cycle whose duration is determined by delay.
	 */
	@Override
	public void run()
	{
		running = true;
		while (running)
		{
			try 
			{
				Thread.sleep(delay);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			// Make sure we are still running, execution may have
			// been paused in between cycles
			if (running)
				timeChanged();
		}
	}

	/**
	 * Pause execution of the timer.
	 */
	public void pause() 
	{
		running = false;
	}
}
