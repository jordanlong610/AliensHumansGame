package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test case for simple timers.
 * @author David Reichard
 *
 */

/**
 * Internal class used for testing SimpleTimers
 */
class MockSimpleTimeObserver implements TimeObserver
{
	public int myTime = 0;
	
	/**
	 * Set myTime equal to time when being updated.
	 */
	public void updateTime(int time)
	{
		myTime = time;
	}
}

public class TestSimpleTimer {

	/**
	 * Test initialization of a SimpleTimer. It should start at round 0,
	 * have no observers, and be an instance of type Timer.
	 */
	@Test
	public void testInitialization()
	{
		SimpleTimer timer = new SimpleTimer();
		assertEquals(0, timer.getRound());
		assertEquals(0, timer.getObserverCount());
		assertTrue(timer instanceof Timer);
	}

	/**
	 * Test that time updates correctly.
	 */
	@Test
	public void testUpdateTime()
	{
		SimpleTimer timer = new SimpleTimer();
		assertEquals(0, timer.getRound());
		timer.timeChanged();
		assertEquals(1, timer.getRound());
	}
	
	/**
	 * Test that we can add an observer to a SimpleTimer.
	 */
	@Test
	public void testAddObserver()
	{
		SimpleTimer timer = new SimpleTimer();
		MockSimpleTimeObserver observer = new MockSimpleTimeObserver();
		timer.addTimeObserver(observer);
		assertEquals(1, timer.getObserverCount());
	}
	
	/**
	 * Test that we can remove an observer from SimpleTimer.
	 */
	@Test
	public void testRemoveObserver()
	{
		SimpleTimer timer = new SimpleTimer();
		MockSimpleTimeObserver observer = new MockSimpleTimeObserver();
		timer.addTimeObserver(observer);
		assertEquals(1, timer.getObserverCount());
		timer.removeTimeObserver(observer);
		assertEquals(0, timer.getObserverCount());
	}
	
	/**
	 * Test that timeChanged works correctly with and without observers.
	 */
	@Test
	public void testTimeChanged()
	{
		SimpleTimer timer = new SimpleTimer();
		MockSimpleTimeObserver observer = new MockSimpleTimeObserver();
		timer.timeChanged(); // should increment round from 0 to 1
		assertEquals(0, observer.myTime); // since observer isn't registered, time should still be 0
		timer.addTimeObserver(observer);
		timer.timeChanged(); // should increment round from 1 to 2
		assertEquals(2, observer.myTime); // now observer is registered, so should receive time of 2
	}
	
	/**
	 * This tests that SimpleTimer will update time correctly as a thread.
	 */
	@Test
	public void testSimpleTimerAsThread() throws InterruptedException
	{
		SimpleTimer st = new SimpleTimer(1000);
		st.start();
		Thread.sleep(250); // So we are 1/4th a second different
		for (int x=0; x<5; x++)
		{
			assertEquals(x, st.getRound()); // assumes round starts at 0
			Thread.sleep(1000); // wait for the next time change
		}
	}
	
	/**
	 * Test that we can stop the SimpleTimer as a thread.
	 */
	@Test
	public void testStopSimpleTimerAsThread() throws InterruptedException
	{
		SimpleTimer st = new SimpleTimer(1000);
		st.start();
		Thread.sleep(1250);
		st.pause();
		assertEquals(1, st.getRound());
		Thread.sleep(1000);
		assertEquals(1, st.getRound());
	}
}
