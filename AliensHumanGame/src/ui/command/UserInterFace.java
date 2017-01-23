package ui.command;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





import weapon.Weapon;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import environment.Environment;


/**
 * User InterFace for the Game.
 * Map, Legend, and Command buttons have all been integrated into one GUI.
 * 
 * @author Jordan Long, Justin Woods
 *
 */
public class UserInterFace extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	JButton textButton, imageButton;
	static JButton attack,acquire,drop,move,reload,turnEast,turnNorth,turnSouth,turnWest;
	JLabel textLabel, imageLabel;
	private Environment e;
	private int rows;
	private int cols;
	
	/**
	 * Creates the User Interface
	 * Builds environment, and GUI.
	 */

	public UserInterFace()
	{
		e = Environment.getWorldInstance(5,5);
		setTitle("Group6_Lab6_GUI");
		setSize(800,800);
		
		rows = e.getWorldRows();
		cols = e.getWorldCols();
		initialization();
		
	}
	
	/**
	 * Create the GUI. Sets panels, background colors, text labels.
	 * @author Justin Woods
	 * 
	 */
	public void initialization()
	{
		
		JPanel pan1 = new JPanel();
		pan1.setBackground(Color.YELLOW);
		JPanel pan2 = new JPanel();
		pan2.setBackground(Color.YELLOW);
		JPanel pan3 = new JPanel();
		JPanel pan4 = new JPanel();
		pan4.setBackground(Color.YELLOW);
		JPanel pan5 = new JPanel();
		pan3.setBackground(Color.YELLOW);
		
		JLabel jl1 = new JLabel("Map");
		pan3.add(jl1);
		
		
		JPanel centerPanel = new JPanel(new GridLayout(rows,cols));
		JLabel[][] labelArray = new JLabel[rows][cols];
		pan5 = new JPanel();
		pan5.setLayout(new BorderLayout());
		for(int r=0;r<rows;r++)
		{
			for(int c=0;c<cols;c++)
			{
				LifeForm entity = e.getLifeForm(r, c);
				
				Weapon wp1 = e.getWeapon(0, r, c);
				Weapon wp2 = e.getWeapon(1, r, c);
				labelArray[r][c] = new JLabel(" ("+r+","+c+") ");
				pan5.add(labelArray[r][c]);
				JPanel nCell = new JPanel(new BorderLayout());
				JLabel weapons = new JLabel();
				
				/**
				 * Constructs GUI for Human if one being created in the Environment.
				 */
				if(entity instanceof Human)
				{
					if(entity.getWeapon() != null)
					{
						imageLabel= new JLabel(humanWithWeapon());
						nCell.add(imageLabel, BorderLayout.CENTER);
					}else
					imageLabel= new JLabel(human());
					nCell.add(imageLabel, BorderLayout.CENTER);
					String directionSt = entity.getDirection();
					if(directionSt == null)
					{
						directionSt = "North";
						JLabel direction = new JLabel("Facing:" + directionSt);
						nCell.add(direction, BorderLayout.NORTH);
					}else
					{
						JLabel direction = new JLabel("Facing:" + directionSt);
						nCell.add(direction, BorderLayout.NORTH);
					}
				} 
				
				/**
				 * Constructs GUI for Alien if one being created in the Environment.
				 */
				if(entity instanceof Alien)
				{
					
					if(entity.getWeapon() != null)
					{
						imageLabel= new JLabel(alienWithWeapon());
						nCell.add(imageLabel, BorderLayout.CENTER);
					}else
					{
						imageLabel= new JLabel(alien());
						nCell.add(imageLabel, BorderLayout.CENTER);
					}
					nCell.add(imageLabel, BorderLayout.CENTER);
					String directionSt = entity.getDirection();
					
					if(directionSt == null)
					{
						directionSt = "North";
						JLabel direction = new JLabel("Facing:" + directionSt);
						nCell.add(direction, BorderLayout.NORTH);
					}else
					{
						JLabel direction = new JLabel("Facing:" + directionSt);
						nCell.add(direction, BorderLayout.NORTH);
					}
					
				}
					
				
				/**
				 * Constructs GUI for weapon if one is present in the environment.
				 */
				if(e.getWeapon(1, r, c) != null)  
				{
					weapons = new JLabel("# Weapons = 1");
					if(e.getWeapon(2, r, c) != null)
					{
						weapons = new JLabel("# Weapons = 2");
					}
				}else
				{
					weapons = new JLabel("# Weapons = 0");
				}
				if((r%2 != 0) && (c%2 == 0))
				{
					nCell.setBackground(Color.WHITE);
					
				}else if ((r%2 == 0) && (c%2 != 0))
				{
					nCell.setBackground(Color.WHITE);
				}
			
				nCell.add(labelArray[r][c], BorderLayout.WEST);
				nCell.add(weapons,BorderLayout.SOUTH);
				
				
				centerPanel.add(nCell);
				
			}
		}
		pan5.add("Center",centerPanel);
	
		/**
		 * Create the buttons for the Commands.
		 * @author Jordan Long
		 */
		
		attack = new JButton("Attack");
		attack.addActionListener(this);
		pan4.add(attack);

		acquire = new JButton("Acquire");
		acquire.addActionListener(this);
		pan4.add(acquire);
		
		drop = new JButton("Drop");
		drop.addActionListener(this);
		pan4.add(drop);
		
		move = new JButton("Move");
		move.addActionListener(this);
		pan4.add(move);
		
		reload = new JButton("Reload");
		reload.addActionListener(this);
		pan4.add(reload);
		
		turnNorth = new JButton("Turn North");
		turnNorth.addActionListener(this);
		pan4.add(turnNorth);
		
		turnSouth = new JButton("Turn South");
		turnSouth.addActionListener(this);
		pan4.add(turnSouth);
		
		turnEast = new JButton("Turn East");
		turnEast.addActionListener(this);
		pan4.add(turnEast);
		
		turnWest = new JButton("Turn West");
		turnWest.addActionListener(this);
		pan4.add(turnWest);
		
		
		
		
		
		
		/**
		 * Create the legend
		 * @author Justin Woods
		 */
		JPanel legendPanel = new JPanel(new GridLayout(3,1, 10, 10));
		add(legendPanel);
		
		imageLabel = new JLabel(human());
		JLabel humanLabel = new JLabel("Human");
		legendPanel.add(imageLabel, BorderLayout.EAST);
		legendPanel.add(humanLabel, BorderLayout.EAST);
		imageLabel= new JLabel(alien());
		legendPanel.add(imageLabel, BorderLayout.EAST);
		JLabel alienLabel = new JLabel("Alien");
		legendPanel.add(alienLabel, BorderLayout.EAST);
		
		imageLabel = new JLabel(weapon());
		legendPanel.add(imageLabel, BorderLayout.EAST);
		JLabel hasWeaponLabel = new JLabel("Has Weapon");
		hasWeaponLabel.setBackground(Color.YELLOW);
		legendPanel.add(hasWeaponLabel, BorderLayout.EAST);
		
		legendPanel.setBackground(Color.YELLOW);

		pan2.add(legendPanel);
		
		add(pan1,BorderLayout.WEST);
		add(pan2,BorderLayout.EAST);
		add(pan3,BorderLayout.NORTH);
		add(pan4,BorderLayout.SOUTH);
		add(pan5,BorderLayout.CENTER);
		setVisible(true);
	}
	/**
	 * Create the image icon for an human with a weapon
	 * @author Justin Woods
	 * @return the image
	 */
	public static ImageIcon humanWithWeapon()
	{
		BufferedImage weaponImage = new 
				BufferedImage(50,50,BufferedImage.BITMASK);
		Graphics drawer = weaponImage.getGraphics();
		drawer.setColor(Color.BLUE);
		drawer.fillRect(0, 0, 200, 200);
		drawer.setColor(Color.RED);
		drawer.fillOval(20, 20, 10, 10);
		return new ImageIcon(weaponImage);
		
	}
	/**
	 * Create the image icon for an alien with a weapon
	 * @author Justin Woods
	 * @return the image
	 */
	public static ImageIcon alienWithWeapon()
	{
		BufferedImage weaponImage = new 
				BufferedImage(50,50,BufferedImage.BITMASK);
		Graphics drawer = weaponImage.getGraphics();
		drawer.setColor(Color.GREEN);
		drawer.fillRect(0, 0, 200, 200);
		drawer.setColor(Color.RED);
		drawer.fillOval(20, 20, 10, 10);
		return new ImageIcon(weaponImage);
		
	}
	/**
	 * Create the Image for human
	 * @author Justin Woods
	 * @return the image
	 */
	public static ImageIcon human()
	{
		BufferedImage humanImage = new 
				BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
		Graphics drawer = humanImage.getGraphics();
		drawer.setColor(Color.BLUE);
		drawer.fillRect(0, 0, 200, 200);
		return new ImageIcon(humanImage);
		
	}

	/**
	 * Create the Image for Alien
	 * @author Justin Woods
	 * @return
	 */
	public static ImageIcon alien()
	{
		BufferedImage alienImage = new 
				BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
		Graphics drawer = alienImage.getGraphics();
		drawer.setColor(Color.GREEN);
		drawer.fillRect(0, 0, 200, 200);
		return new ImageIcon(alienImage);
		
	}
	/**
	 * Create the image for a life form with a weapon
	 * @author Justin Woods
	 * @return
	 */
	public static ImageIcon weapon()
	{
		BufferedImage weaponImage = new 
				BufferedImage(50,50,BufferedImage.BITMASK);
		Graphics drawer = weaponImage.getGraphics();
		
		drawer.setColor(Color.RED);
		drawer.fillOval(20, 20, 10, 10);
		return new ImageIcon(weaponImage);
		
	}
	
	
	public static void main(String[] args)
	{
		new InvokerBuilder();
		new UserInterFace();
		
		
	}



	/**
	 * When a button is pressed, its corresponding action will be performed.
	 * @author Jordan Long
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == acquire)
		{
			acquire.setText("Pushed");
			Invoker.pressButton(0,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == attack)
		{
			attack.setText("Pushed");
			Invoker.pressButton(1,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == drop)
		{
			drop.setText("Pushed");
			Invoker.pressButton(2,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == move)
		{
			move.setText("Pushed");
			Invoker.pressButton(3,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == reload)
		{
			reload.setText("Pushed");
			Invoker.pressButton(4,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == turnEast)
		{
			turnEast.setText("Pushed");
			Invoker.pressButton(5,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == turnNorth)
		{
			turnNorth.setText("Pushed");
			Invoker.pressButton(6,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == turnSouth)
		{
			turnSouth.setText("Pushed");
			Invoker.pressButton(7,e.getselectedLFRow(), e.getselectedLFCol());
		}
		if(event.getSource() == turnWest)
		{
			turnWest.setText("Pushed");
			Invoker.pressButton(8,e.getselectedLFRow(), e.getselectedLFCol());
		}
		
	}
	



}
