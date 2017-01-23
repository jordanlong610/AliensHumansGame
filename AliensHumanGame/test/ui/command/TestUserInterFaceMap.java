package ui.command;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import lifeform.Alien;
import lifeform.Human;
import lifeform.MockLifeForm;

import org.junit.Test;

import recovery.RecoveryLinear;
import ui.command.UserInterFace;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;
import environment.Cell;
import environment.Environment;
import exceptions.InvalidRateOfRecoveryException;
/**
 *  Test the functionality of the GUI
 * @author Justin Woods
 *
 */
public class TestUserInterFaceMap 
{
	/**
	 * Test to see if the human is displayed correctly
	 * @throws Exception
	 */
	@Test
	public void testHumanDisplay() throws Exception 
	{
		
		UserInterFace gui = new UserInterFace();
		
		Environment e =  Environment.getWorldInstance(6, 6);
		Human bob = new Human("Bob", 50, 10);
		
		e.addLifeForm(bob, 0, 0);
		
		gui.initialization();
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Create Cell Image Icon Correct For\nHuman(0,0)\nDoes it look right?"));
		e.clearEnvironment();
		
		
	}
	/**
	 * Test to see if the alien is displayed correctly
	 * @throws Exception
	 */
	@Test
	public void testAlienDisplay() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		Environment e = Environment.getWorldInstance(5, 5);
		Alien zee = new Alien("Zee", 10);
		e.addLifeForm(zee, 1, 1);
		
		gui.initialization();
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Create Cell Image Icon Correct For\nAlien(1,1)\nDoes it look right?"));
		e.clearEnvironment();
	
	}
	/**
	 * Test to see if a life form has a weapon
	 * @throws Exception
	 */
	@Test
	public void testHasWeaponDisplay() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		Human bob = new Human("Bob", 50, 10);
		Weapon gun = new Pistol();
		Environment e = Environment.getWorldInstance(5, 5);
		bob.setWeapon(gun);
		
		e.addLifeForm(bob, 0, 0);
		
		gui.initialization();
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Create Cell Image Icon Correct For\nHas Weapon(0,0)\nDoes it look right?"));
		e.clearEnvironment();
		
	}
	/**
	 * Test to see if one weapon is displayed to cell with a pistol
	 * @throws Exception
	 */
	@Test
	public void testOneCellWeaponDisplay() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		Weapon pistol = new Pistol();
		Environment e = Environment.getWorldInstance(5, 5);
		
		e.addWeapon(pistol, 0, 0);
		gui.initialization();
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Create Cell Image Icon Correct For\nHas 1 weapon in cell(0,0)\nDoes it look right?"));
		e.clearEnvironment();
		
	}
	/**
	 * Test to see if two weapon is displayed to cell with a Plasma cannon and chain gun
	 * @throws Exception
	 */
	@Test
	public void testTwoCellWeaponDisplay() throws Exception 
	{
		UserInterFace gui = new UserInterFace();
		Weapon pc = new PlasmaCannon();
		Weapon chaingun = new ChainGun();
		Environment e = Environment.getWorldInstance(5,5);
		
		e.addWeapon(pc, 0, 0);
		e.addWeapon(chaingun, 0, 0);
		gui.initialization();
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Create Cell Image Icon Correct For\nHas 2 weapon in cell(0,0)\nDoes it look right?"));
		e.clearEnvironment();
		
	}


}
