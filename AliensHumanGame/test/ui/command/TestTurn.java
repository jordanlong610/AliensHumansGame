package ui.command;

import static org.junit.Assert.*;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
/**
 * tests the functionality of the turn(Direction) classes
 * @author Jeff Titanich
 */
public class TestTurn
{
	
	
	//	LAB 6 (COMMAND) TESTS BEGIN HERE
	
	
	Environment e = Environment.getWorldInstance(2, 2);
	MockLifeForm m = new MockLifeForm("bob", 10);
	
	//	set up the Environment for all tests
	@Before
	public void before()
	{
		e.addLifeForm(m, 0, 0);
	}
	
	//	turnNorth execute method works correctly
	@Test
	public void testTurnNorth()
	{
		Environment e = Environment.getWorldInstance();
		LifeForm m = e.getLifeForm(0, 0);
		m.changeDirection("South");
		
		Command north = new turnNorth(e);
		north.execute(0, 0);
		assertEquals("North", m.getDirection());
	}
	
	//	turnSouth execute method works correctly
	@Test
	public void testTurnSouth()
	{
		Environment e = Environment.getWorldInstance();
		LifeForm m = e.getLifeForm(0, 0);
		
		Command south = new turnSouth(e);
		south.execute(0, 0);
		assertEquals("South", m.getDirection());
	}
	
	//	turnEast execute method works correctly
	@Test
	public void testTurnEast()
	{
		Environment e = Environment.getWorldInstance();
		LifeForm m = e.getLifeForm(0, 0);
		
		Command east = new turnEast(e);
		east.execute(0, 0);
		assertEquals("East", m.getDirection());
	}
	
	//	turnWest execute method works correctly
	@Test
	public void testTurnWest()
	{
		Environment e = Environment.getWorldInstance();
		LifeForm m = e.getLifeForm(0, 0);
		
		Command west = new turnWest(e);
		west.execute(0, 0);
		assertEquals("West", m.getDirection());
	}
}
