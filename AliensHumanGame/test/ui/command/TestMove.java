package ui.command;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import lifeform.MockLifeForm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import environment.Environment;
/**
 * tests the functionality of the Move class
 * @author Jeff Titanich, Josh Varone (user-selection test in bonus)
 */
public class TestMove
{	
	
	
	//	LAB 6 (COMMAND) TESTS BEGIN HERE

	
	//	Environment is large enough to have a LifeForm move from the
	//	center cell in any direction at max speed and not hit an edge
	Environment e;
	MockLifeForm m = new MockLifeForm("bob", 10);
	
	//	set up the Environment for all tests
	@Before
	public void before()
	{
		e = Environment.getWorldInstance();
		e.clearEnvironment();
		e = Environment.getWorldInstance(9, 9);
		e.addLifeForm(m, 4, 4);
	}
	
	//	clean up the Environment after all tests
	@After
	public void cleanup()
	{
		e.clearEnvironment();
	}
	
	//	LifeForm moves max speed north with no obstacles
	@Test
	public void testMoveNorthFullSpeed()
	{
		Command move = new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(4, 4);
		assertEquals(m, e.getLifeForm(1, 4));
	}
	
	//	LifeForm moves max speed south with no obstacles
	@Test
	public void testMoveSouthFullSpeed()
	{
		m.changeDirection("South");
		Command move = new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(4, 4);
		assertEquals(m, e.getLifeForm(7, 4));
	}
	
	//	LifeForm moves max speed east with no obstacles
	@Test
	public void testMoveEastFullSpeed()
	{
		m.changeDirection("East");
		Command move = new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(4, 4);
		assertEquals(m, e.getLifeForm(4, 7));
	}
	
	//LifeForm moves max speed west with no obstacles
	@Test
	public void testMoveWestFullSpeed()
	{
		m.changeDirection("West");
		Command move = new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(4, 4);
		assertEquals(m, e.getLifeForm(4, 1));
	}
	
	
	//	LAB 6 BONUS TESTS BEGIN HERE
	
	
	//LifeForm moves max speed - 1 if there's an obstacle
	@Test
	public void testMoveSpeedMinusOne()
	{
		MockLifeForm obstacle = new MockLifeForm("obstacle", 10);
		e.addLifeForm(obstacle, 1, 4);
		Command move = new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(4, 4);
		assertEquals(m, e.getLifeForm(2, 4));
	}
	
	//LifeForm moves max speed if there's an obstacle before max speed
	@Test
	public void testMoveOverObstacle()
	{
		MockLifeForm obstacle = new MockLifeForm("obstacle", 10);
		e.addLifeForm(obstacle, 2, 4);
		Command move =  new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(4, 4);
		assertEquals(m, e.getLifeForm(1, 4));
	}
	
	//	LifeForm can't move past the edge of the map
	@Test
	public void testMoveToEdge()
	{
		Command move = new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(4, 4);
		JOptionPane.showMessageDialog(null, "Please select 3 spaces in the next dialog box.");
		move.execute(1, 4);
		assertEquals(m, e.getLifeForm(0, 4));
	}
	
	/**
	 * Tests that a LifeForm moves the user-specified number of spaces
	 * if less than their maxSpaces.
	 * @author Josh Varone
	 */
	@Test
	public void testMoveUserSpecifiedSpaces()
	{
		Command move = new Move(e);
		JOptionPane.showMessageDialog(null, "Please select 1 space in the next dialog box.");
		move.execute(4, 4);
		assertEquals(m, e.getLifeForm(3, 4));
		JOptionPane.showMessageDialog(null, "Please select 2 spaces in the next dialog box.");
		move.execute(3, 4);
		assertEquals(m, e.getLifeForm(1, 4));
	}
}
