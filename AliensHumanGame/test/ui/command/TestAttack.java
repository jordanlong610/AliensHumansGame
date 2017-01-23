package ui.command;

import static org.junit.Assert.*;
import lifeform.MockLifeForm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weapon.Pistol;
import environment.Environment;
/**
 * tests the functionality of the Attack class
 * @author Jeff Titanich
 */
public class TestAttack
{
	
	
	//	LAB 6 (COMMAND) TESTS BEGIN HERE
	
	
	Environment e = Environment.getWorldInstance(9, 9);
	MockLifeForm m = new MockLifeForm("bob", 10);
	
	//	set up the Environment for all tests
	@Before
	public void before()
	{
		e.addLifeForm(m, 4, 4);
		Pistol p = new Pistol();
		m.setWeapon(p);
	}
	
	//	clean up the Environment after all tests
	@After
	public void cleanup()
	{
		e.clearEnvironment();
	}
	
	//	attacks if facing north and an enemy is north
	@Test
	public void testEnemyNorth()
	{
		MockLifeForm enemy = new MockLifeForm("enemy", 1);
		e.addLifeForm(enemy, 0, 4);
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(9, m.getWeapon().getCurrentAmmo());
	}
	
	//	attacks if facing south and an enemy is south
	@Test
	public void testEnemySouth()
	{
		e.getLifeForm(4, 4).changeDirection("South");
		MockLifeForm enemy = new MockLifeForm("enemy", 1);
		e.addLifeForm(enemy, 8, 4);
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(9, m.getWeapon().getCurrentAmmo());
	}
	
	//	attacks if facing east and an enemy is east
	@Test
	public void testEnemyEast()
	{
		e.getLifeForm(4, 4).changeDirection("East");
		MockLifeForm enemy = new MockLifeForm("enemy", 1);
		e.addLifeForm(enemy, 4, 8);
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(9, m.getWeapon().getCurrentAmmo());
	}
	
	//	attacks if facing west and an enemy is west
	@Test
	public void testEnemyWest()
	{
		e.getLifeForm(4, 4).changeDirection("West");
		MockLifeForm enemy = new MockLifeForm("enemy", 1);
		e.addLifeForm(enemy, 4, 0);
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(9, m.getWeapon().getCurrentAmmo());
	}
	
	
	//	BONUS TESTS FOR LAB 6 (COMMAND) BEGIN HERE
	
	
	//	doesn't attack if facing north and there's no enemy north
	@Test
	public void testFacingNorthNoEnemy()
	{
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(10, m.getWeapon().getCurrentAmmo());
	}
	
	//	doesn't attack if facing south and there's no enemy south
	@Test
	public void testFacingSouthNoEnemy()
	{
		e.getLifeForm(4, 4).changeDirection("South");
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(10, m.getWeapon().getCurrentAmmo());
	}
	
	//	doesn't attack if facing east and there's no enemy east
	@Test
	public void testFacingEastNoEnemy()
	{
		e.getLifeForm(4, 4).changeDirection("East");
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(10, m.getWeapon().getCurrentAmmo());
	}
	
	//	doesn't attack if facing west and there's no enemy west
	@Test
	public void testFacingWestNoEnemy()
	{
		e.getLifeForm(4, 4).changeDirection("West");
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(10, m.getWeapon().getCurrentAmmo());
	}
	
	//	attacks the closest enemy
	@Test
	public void testClosestEnemy()
	{
		MockLifeForm enemy1 = new MockLifeForm("enemy1", 1);
		e.addLifeForm(enemy1, 0, 4);
		MockLifeForm enemy2 = new MockLifeForm("enemy2", 1);
		e.addLifeForm(enemy2, 1, 4);
		Command attack = new Attack(e);
		attack.execute(4, 4);
		assertEquals(9, m.getWeapon().getCurrentAmmo());
		
		//	only damaged the closer enemy
		assertEquals(0, enemy2.getCurrentLifePoints());
		assertEquals(1, enemy1.getCurrentLifePoints());
	}
}
