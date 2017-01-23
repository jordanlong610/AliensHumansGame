package ui.command;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

import ui.command.UserInterFace;
/**
 * Test the functionality of the GUI
 * @author Justin Woods
 *
 */
public class TestUserInterFaceLegend {
	/**
	 * Test to see human is displayed in the GUI legend
	 */
	@Test
	public void testHumanLegend() 
	{
		UserInterFace gui = new UserInterFace();
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Map Legend Displays Correctly\nHuman(Blue Square)\nDoes it look right?"));
	}
	
	/**
	 * Test to see alien is displayed in the GUI legend
	 */
	@Test
	public void testAlienLegend() 
	{
		UserInterFace gui = new UserInterFace();
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Map Legend Displays Correctly\nAlien(Green Square)\nDoes it look right?"));
	}

	/**
	 * Test to see has weapon is displayed in the GUI legend
	 */
	@Test
	public void testWeaponLegend() 
	{
		UserInterFace gui = new UserInterFace();
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Map Legend Displays Correctly\nWeapon(Red Circle)\nDoes it look right?"));
	}
}
