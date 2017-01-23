package environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.EnvironmentException;
import exceptions.InvalidRateOfRecoveryException;
import weapon.*;

/**
 * Test cases for the Environment class.
 * @author David Reichard
 * @author Josh Varone - added Lab 5 Singleton and Lab 6 Command functionality
 *
 */
public class TestEnvironment 
{		
	/**
	 * START tests for Command Lab
	 */
	
	/**
	 * Tests that a Human moves correctly North when it jumps an obstacle.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveNorthJump()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		//Test jumping obstacle North (human should end at 1, 2)
		e.addLifeForm(human, 4, 2);
		e.addLifeForm(obstacle, 3, 2);
		assertTrue(e.move(human,  3));
		assertEquals(human, e.getLifeForm(1, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that a Human moves correctly North when there is an obstacle
	 * in its destination.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveNorthHitObstacle()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		//Test moving to obstacle's location (human should end at 2, 2)
		e.addLifeForm(human, 4, 2);
		e.addLifeForm(obstacle, 1, 2);
		assertTrue(e.move(human, 3));
		assertEquals(human, e.getLifeForm(2, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that a Human moves correctly North with no obstacles.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveNorthFreely()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		//Test moving without obstacle North (human should end at 1, 2)
		e.addLifeForm(human, 4, 2);
		assertTrue(e.move(human, 3));
		assertEquals(human, e.getLifeForm(1, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that a Human moves correctly South when it jumps an obstacle.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveSouthJump()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		human.changeDirection("South");
		//Test jumping obstacle South (human should end at 4, 2)
		e.addLifeForm(human, 1, 2);
		e.addLifeForm(obstacle, 2, 2);
		assertTrue(e.move(human,  3));
		assertEquals(human, e.getLifeForm(4, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that a Human moves correctly South when an obstacle is in
	 * its destination cell.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveSouthHitObstacle()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		human.changeDirection("South");
		//Test moving to obstacle's location (human should end at 3, 2)
		e.addLifeForm(human, 1, 2);
		e.addLifeForm(obstacle, 4, 2);
		assertTrue(e.move(human, 3));
		assertEquals(human, e.getLifeForm(3, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that a Human moves correctly South with no obstacles.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveSouthFreely()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		human.changeDirection("South");
		//Test moving without obstacle South (human should end at 4, 2)
		e.addLifeForm(human, 1, 2);
		assertTrue(e.move(human, 3));
		assertEquals(human, e.getLifeForm(4, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that an Alien moves correctly East when it jumps an obstacle.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveEastJump() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		alien.changeDirection("East");
		//Test jumping obstacle East (alien should end at 2, 3)
		e.addLifeForm(alien, 2, 1);
		e.addLifeForm(obstacle, 2, 2);
		assertTrue(e.move(alien, 2));
		assertEquals(alien, e.getLifeForm(2, 3));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that an Alien moves correctly East when there is an obstacle
	 * in its destination.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveEastHitObstacle() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		alien.changeDirection("East");
		//Test moving to obstacle's location (alien should end at 2, 2)
		e.addLifeForm(alien, 2, 1);
		e.addLifeForm(obstacle, 2, 3);
		assertTrue(e.move(alien, 2));
		assertEquals(alien, e.getLifeForm(2, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that an Alien moves correctly East with no obstacles.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveEastFreely() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		alien.changeDirection("East");
		//Test moving without obstacle East (alien should end at 2, 3)
		e.addLifeForm(alien, 2, 1);
		assertTrue(e.move(alien, 2));
		assertEquals(alien, e.getLifeForm(2, 3));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that an Alien moves correctly West when it jumps an obstacle.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveWestJump() throws InvalidRateOfRecoveryException
	{	
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		alien.changeDirection("West");
		//Test jumping obstacle West (alien should end at 2, 1)
		e.addLifeForm(alien, 2, 3);
		e.addLifeForm(obstacle, 2, 2);
		assertTrue(e.move(alien, 2));
		assertEquals(alien, e.getLifeForm(2, 1));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that an Alien moves correctly West both when there is an obstacle
	 * in its destination.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveWestHitObstacle() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		alien.changeDirection("West");
		//Test moving to obstacle's location (alien should end at 2, 2)
		e.addLifeForm(alien, 2, 3);
		e.addLifeForm(obstacle, 2, 1);
		assertTrue(e.move(alien, 2));
		assertEquals(alien, e.getLifeForm(2, 2));
		e.clearEnvironment();
	}
	
	/**
	 * Tests that an Alien moves correctly West with no obstacles.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveWestFreely() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		alien.changeDirection("West");
		//Test moving without obstacle West (alien should end at 2, 1)
		e.addLifeForm(alien, 2, 3);
		assertTrue(e.move(alien, 2));
		assertEquals(alien, e.getLifeForm(2, 1));
		e.clearEnvironment();
	}
	
	/*******************************
	 * BONUS TESTS for Command Lab *
	 *******************************/
	/**
	 * Tests that a LifeForm can move the specified number of spaces, rather
	 * than only going the default maxSpaces.
	 * @author Josh Varone
	 */
	@Test
	public void testMoveLessThanMax()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf = new MockLifeForm("Bob", 50);
		e.addLifeForm(lf, 4, 0);
		//despite maxSpaces being 3, lf should only move the specified spaces
		assertTrue(e.move(lf, 1));
		assertEquals(lf, e.getLifeForm(3, 0));
		assertTrue(e.move(lf, 2));
		assertEquals(lf, e.getLifeForm(1, 0));
		lf.changeDirection("East");
		assertTrue(e.move(lf, 3));
		assertEquals(lf, e.getLifeForm(1, 3));
	}
	
	/**
	 * Tests that a Human fails to move North when it has nowhere to go.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveNorthFail()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		//Test unable to move in specified direction
		e.addLifeForm(human, 0, 3);
		assertFalse(e.move(human, 3));
	}
	
	/**
	 * Tests that a Human does not move South when it has nowhere to go.
	 * @author Josh Varone
	 */
	@Test
	public void testHumanMoveSouthFail()
	{
		Human human = new Human("Bob", 30);
		Environment e = Environment.getWorldInstance(5, 5);
		human.changeDirection("South");
		//Test unable to move in specified direction
		e.addLifeForm(human, 4, 3);
		assertFalse(e.move(human, 3));
	}

	/**
	 * Tests that an Alien fails to move East when it has nowhere to go.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveEastFail() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm obstacle = new MockLifeForm("Obstacle", 50);
		alien.changeDirection("East");
		//Test unable to move in specified direction
		e.addLifeForm(alien, 2, 4);
		assertFalse(e.move(alien, 2));
	}
	
	/**
	 * Tests that an Alien fails to move West when it has nowhere to go.
	 * @author Josh Varone
	 */
	@Test
	public void testAlienMoveWest() throws InvalidRateOfRecoveryException
	{
		Alien alien = new Alien("Al", 50);
		Environment e = Environment.getWorldInstance(5, 5);
		alien.changeDirection("West");
		//Test unable to move in specified direction
		e.addLifeForm(alien, 2, 0);
		assertFalse(e.move(alien, 2));
	}
	
	/**
	 * Tests that a LifeForm cannot move more than maxSpaces if a larger number is
	 * specified as an argument.
	 * @author Josh Varone
	 */
	@Test
	public void testMoveMoreThanMax()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf = new MockLifeForm("Bob", 50);
		e.addLifeForm(lf, 4, 2);
		//When a number greater than maxSpaces is given, should only move maxSpaces
		assertTrue(e.move(lf, 27));
		assertEquals(lf, e.getLifeForm(1, 2));
		//lf should only move to the boundary, even when a large number is given
		assertTrue(e.move(lf, 12));
		assertEquals(lf, e.getLifeForm(0, 2));
	}
	
	/*
	 * BONUS TESTS for Command Lab (potential)
	 * The below tests were asked for in the lab manual but were not on the rubric.
	 ********************/
	
	/**
	 * Tests that a LifeForm moves correctly when there is enough
	 * space and no obstacles.
	 * @author Josh Varone
	 */
	@Test
	public void testMoveFreely()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf = new MockLifeForm("Bob", 50);
		e.addLifeForm(lf, 4, 3);
		assertEquals(lf, e.getLifeForm(4, 3));
		assertTrue(e.move(4, 3, 3));
		//lf should be 3 spaces north, in row 1
		assertEquals(lf, e.getLifeForm(1, 3));
		lf.changeDirection("West");
		assertTrue(e.move(1, 3, 3));
		//lf should be 3 spaces west, in column 0
		assertEquals(lf, e.getLifeForm(1, 0));
		lf.changeDirection("South");
		assertTrue(e.move(1, 0, 2));
		//lf should be 2 spaces south, in row 3
		assertEquals(lf, e.getLifeForm(3, 0));
		lf.changeDirection("East");
		assertTrue(e.move(3, 0, 4));
		//lf should only move 3 spaces east, to column 3
		assertEquals(lf, e.getLifeForm(3, 3));
	}
	
	/**
	 * Tests that a LifeForm moves correctly when it goes to the
	 * boundaries of the Environment.
	 * @author Josh Varone
	 */
	@Test
	public void testMoveCloseToBounds()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf = new MockLifeForm("Bob", 50);
		e.addLifeForm(lf, 1, 2);
		assertTrue(e.move(lf, 3));
		//lf should move north, but stop at edge cell (row 0)
		assertEquals(lf, e.getLifeForm(0, 2));
		lf.changeDirection("West");
		assertTrue(e.move(lf, 3));
		//lf should move west, but stop at edge cell (col 0)
		assertEquals(lf, e.getLifeForm(0, 0));
		lf.changeDirection("South");
		assertTrue(e.move(lf, 3));
		assertTrue(e.move(lf, 3));
		//although told to go 6 spaces, lf stops at 5 (row 4)
		assertEquals(lf, e.getLifeForm(4, 0));
		//moving again should be false because it is invalid
		assertFalse(e.move(lf, 3));
		lf.changeDirection("East");
		assertTrue(e.move(lf, 3));
		assertTrue(e.move(lf,  3));
		//lf is told to move 6 spaces, but stops at 5 (col 4)
		assertEquals(lf, e.getLifeForm(4, 4));
		//moving again should be false because it is invalid
		assertFalse(e.move(lf, 3));
	}
	
	/**
	 * Tests that a LifeForm moves correctly when there are obstacles.  Tests
	 * when an obstacle is in the destination and in the path for both row
	 * and column calculations (i.e. North/South, East/West).
	 * in its way.
	 * @author Josh Varone
	 */
	@Test
	public void testMoveWithObstacles()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf = new MockLifeForm("Bob", 50);
		LifeForm ob1 = new MockLifeForm("Obstacle1", 20);
		LifeForm ob2 = new MockLifeForm("Obstacle2", 20);
		LifeForm ob3 = new MockLifeForm("Obstacle3", 20);
		LifeForm ob4 = new MockLifeForm("Obstacle4", 20);
		e.addLifeForm(lf, 4, 1);
		e.addLifeForm(ob1, 1, 1);		
		assertTrue(e.move(lf, 3));
		//lf should go north 3, but there's an obstacle, so it will go 2
		assertEquals(lf, e.getLifeForm(2, 1));
		e.addLifeForm(ob2, 2, 3);
		lf.changeDirection("East");
		assertTrue(e.move(lf, 3));
		//lf should move 3 spaces east, jumping ob2 and landing in col 4
		assertEquals(lf, e.getLifeForm(2, 4));
		e.addLifeForm(ob3, 3, 4);
		lf.changeDirection("South");
		assertTrue(e.move(lf, 2));
		//lf should move south 2, jumping ob3 and landing in row 4
		assertEquals(lf, e.getLifeForm(4, 4));
		e.addLifeForm(ob4, 4, 1);
		lf.changeDirection("West");
		assertTrue(e.move(lf, 3));
		//lf should move west 3, but with obstacle, it will only go 2
		assertEquals(lf, e.getLifeForm(4, 2));
	}
	
	
	/**********************************
	 * START Singleton Lab tests
	 **********************************/
	
	/**
	 * Clear all cells in the Environment before each test is run
	 * to ensure there are no remaining LifeForms or weapons in either.
	 * @author Josh Varone
	 */
	@Before
	public void clearEnvironment()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		e.clearEnvironment();
	}
	
	/**
	 * Create a 5x5 environment, test that all cells in it are empty.
	 * @author Josh Varone - initialize as Singleton
	 */
	@Test
	public void testInitialization()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		
		//make sure every cell is empty
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
			{
				assertNull(e.getLifeForm(i,j));
				assertNull(e.getWeapon(1, i, j));
				assertNull(e.getWeapon(2, i, j));
			}
		
		//to show that it is Singleton, we will add a LifeForm, create a new
		//Environment f (which should have the same instance as e), and test
		//that the same LifeForm is at the same location in f.
		LifeForm entity = new MockLifeForm("Al", 50);
		e.addLifeForm(entity, 1, 2);
		Environment f = Environment.getWorldInstance(7, 7);
		assertEquals(entity, f.getLifeForm(1, 2));
		//additionally, the different parameter values in f have no effect
		//because the environment already exists; a row coordinate of 6 is
		//still invalid
		assertFalse(f.addLifeForm(entity, 6, 2));
	}
	
	/**
	 * Tests that two weapons can be added to specified cell within
	 * the environment.
	 * @author Josh Varone
	 */
	@Test
	public void testAddWeapons()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		Weapon wp1 = new Pistol();
		Weapon wp2 = new PlasmaCannon();
		//there should not be weapons in the cell
		assertNull(e.getWeapon(1, 0, 0));
		assertNull(e.getWeapon(2, 0, 0));
		//two weapons should add successfully
		assertTrue(e.addWeapon(wp1, 0, 0));
		assertTrue(e.addWeapon(wp2, 0, 0));
		assertEquals(wp1, e.getWeapon(1, 0, 0));
		assertEquals(wp2, e.getWeapon(2, 0, 0));
	}
	
	/**
	 * Tests that weapons can be removed from the specified cell in
	 * the environment.
	 * @author Josh Varone
	 */
	@Test
	public void testRemoveWeapons()
	{
		Environment e = Environment.getWorldInstance(5, 5);
		Weapon wp1 = new Pistol();
		Weapon wp2 = new PlasmaCannon();
		e.addWeapon(wp1, 0, 0);
		e.addWeapon(wp2, 0, 0);
		//removing them should return the weapon from that position
		assertEquals(wp1, e.removeWeapon(1, 0, 0));
		assertEquals(wp2, e.removeWeapon(2, 0, 0));
		//now both positions should be null
		assertNull(e.getWeapon(1, 0, 0));
		assertNull(e.getWeapon(2, 0, 0));
	}
	
	/**
	 * Tests that range is computed correctly when LifeForms are in
	 * the same row.
	 * @author Josh Varone
	 */
	@Test
	public void testRangeSameRow() throws EnvironmentException
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf1 = new MockLifeForm("Al", 50);
		LifeForm lf2 = new MockLifeForm("Bob", 50);
		//both should be successfully added to environment
		assertTrue(e.addLifeForm(lf1, 1, 0));
		assertTrue(e.addLifeForm(lf2, 1, 3));
		//distance in same row should be abs. value of (lf1 col - lf2 col) times 5
		//so here it should be (3 - 0) * 5 = 15
		assertEquals(15, e.getDistance(lf1, lf2));
	}
	
	/**
	 * Tests that range is computed correctly when LifeForms are in
	 * the same column.
	 * @author Josh Varone
	 */
	@Test
	public void testRangeSameCol() throws EnvironmentException
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf1 = new MockLifeForm("Al", 50);
		LifeForm lf2 = new MockLifeForm("Bob", 50);
		//both should be successfully added to environment
		assertTrue(e.addLifeForm(lf1, 2, 1));
		assertTrue(e.addLifeForm(lf2, 4, 1));
		//distance in same column should be abs. value of (lf1 row - lf2 row) times 5
		//so here it should be (4 - 2) * 5 = 10
		assertEquals(10, e.getDistance(lf1, lf2));
	}
	
	/**Tests that range is computed correctly when LifeForms are neither
	 * in the same row nor same column.
	 */
	@Test
	public void testRangeDifferentRowCol() throws EnvironmentException
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf1 = new MockLifeForm("Al", 50);
		LifeForm lf2 = new MockLifeForm("Bob", 50);
		//both should be successfully added to environment
		assertTrue(e.addLifeForm(lf1, 0, 0));
		assertTrue(e.addLifeForm(lf2, 4, 4));
		//distance in this case should be square root of (((lf1 row - lf2 row)*5)^2
		//plus ((lf1 column - lf2 column)*5)^2)
		//so it is sqrt(((4-0)*5)^2+((4-0)*5)^2) = sqrt(400+400) = 28 (truncated)
		assertEquals(28, e.getDistance(lf1, lf2));
	}
	
	/***************
	 * BONUS TESTS for Singleton Lab
	 ***************/
	
	/**
	 * Tests that getDistance(row1, row2, col1, col2) also calculates damage correctly
	 * @author Josh Varone
	 */
	@Test
	public void testGetDistanceCoordinates() throws EnvironmentException
	{
		Environment e = Environment.getWorldInstance(5, 5);
		assertEquals(15, e.getDistance(1, 0, 1, 3));
		assertEquals(10, e.getDistance(2, 1, 4, 1));
		assertEquals(28, e.getDistance(0, 0, 4, 4));
	}
	
	/**
	 * Tests that an EnvironmentException will be thrown if invalid coordinates
	 * are input to getDistance
	 * @author Josh Varone
	 */
	@Test(expected = EnvironmentException.class)
	public void testEnvironmentException() throws EnvironmentException
	{
		Environment e = Environment.getWorldInstance(5, 5);
		e.getDistance(1, 2, 8, 3);
	}
	
	/**
	 * Tests that an EnvironmentException will be thrown if one LifeForm is not
	 * in the Environment and getDistance is called
	 * @author Josh Varone
	 */
	@Test(expected = EnvironmentException.class)
	public void testLifeFormOutOfEnviro() throws EnvironmentException
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf1 = new MockLifeForm("Ali", 50);
		LifeForm lf2 = new MockLifeForm("Bob", 50);
		e.addLifeForm(lf1, 3, 2);
		e.getDistance(lf1, lf2);
	}
	
	/**
	 * Tests that no damage will result between LifeForms if one is not in
	 * the Environment
	 * @author Josh Varone
	 */
	@Test()
	public void testNoDamageIfNotInEnviro() throws EnvironmentException
	{
		Environment e = Environment.getWorldInstance(5, 5);
		LifeForm lf1 = new MockLifeForm("Al", 50);
		LifeForm lf2 = new MockLifeForm("Bob", 50);
		assertTrue(e.addLifeForm(lf1, 0, 0));
		//don't add lf2 to the environment
		lf1.setAttackStrength(20);
		lf1.attack(lf2);
		//because they are not both in the Environment, no damage will occur
		assertEquals(50, lf2.getCurrentLifePoints());
	}

	/********************************
	 * START Strategy Pattern Tests *
	 ********************************/
	
	/**
	 * Test adding life forms to an environment.
	 */
	@Test
	public void testAddLifeForm()
	{
		Environment environment = Environment.getWorldInstance(2, 3);
		MockLifeForm entity = new MockLifeForm("Bob", 40);
		environment.addLifeForm(entity, 1, 2);
		assertEquals(entity, environment.getLifeForm(1, 2));
	}
	
	/**
	 * Test adding a life form to an invalid location.
	 */
	@Test
	public void testAddLifeFormInvalid()
	{
		Environment environment = Environment.getWorldInstance(5,5);
		MockLifeForm entity = new MockLifeForm("Bob", 40);
		assertFalse(environment.addLifeForm(entity, 6, 1)); // invalid column
		assertFalse(environment.addLifeForm(entity, 1, 7)); // invalid row
		assertFalse(environment.addLifeForm(entity, -1, 2)); // negative column
		assertFalse(environment.addLifeForm(entity, 1, -1)); // negative row
	}
	
	/**
	 * Test removing life forms from an environment.
	 */
	@Test
	public void testRemoveLifeForm()
	{
		Environment environment = Environment.getWorldInstance(5, 5);
		MockLifeForm entity = new MockLifeForm("Bob", 40);
		environment.addLifeForm(entity, 1, 2);
		// assert that there is currently a life form in location
		assertEquals(entity, environment.getLifeForm(1, 2));
		assertTrue(environment.removeLifeForm(1, 2));
		// now getLifeForm() should return null indicating no life form exists at position
		assertNull(environment.getLifeForm(1, 2));
	}
}
