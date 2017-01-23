package lifeform;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.OutOfAmmoException;
import weapon.Weapon;
import gameplay.TimeObserver;

/**
 * Keeps track of the information associated with a simple life form.
 * Also provides the functionality related to the life form.
 * @author David Reichard
 * @author Joshua Varone - weapon-related methods, Command Lab methods
 *
 */
public abstract class LifeForm implements TimeObserver
{
	private String myName;			 //name of the LifeForm
	protected int currentLifePoints; //life points of the LifeForm
	private int attackStrength;		 //attack strength of the LifeForm
	protected int round;			 //current round of the game
	private Weapon weapon;			 //current weapon of the LifeForm
	private String currentDirection; //direction the LifeForm is facing
	protected int maxSpeed;			 //max spaces LifeForm moves in a round
	
	/**
	 * Create an instance
	 * 
	 * @param name the name of the life form
	 * @param points the current starting life points of the life form
	 */
	public LifeForm(String name, int points)
	{
		myName = name;
		currentLifePoints = points;
		currentDirection = "North";
		maxSpeed = 0;
	}
	
	/**
	 * @return the name of the life form.
	 */
	public String getName()
	{
		return myName;
	}
	
	/**
	 * @return the amount of current life points the life form has
	 */
	public int getCurrentLifePoints()
	{
		return currentLifePoints;
	}
	
	/**
	 * Take damage.
	 * @param damage Amount of damage to apply to life form.
	 */
	public void takeHit(int damage)
	{
		currentLifePoints -= damage;
		if (currentLifePoints < 0)
			currentLifePoints = 0;
	}
	
	/**
	 * Set attack strength.
	 * @param attack Attack strength to apply to life form.
	 */
	public void setAttackStrength(int attack)
	{
		attackStrength = attack;
	}
	
	/**
	 * Get attack strength of life form.
	 * @return attack strength of life form.
	 */
	public int getAttackStrength()
	{
		return attackStrength;
	}
	
	/**
	 * Attack another life form. Can not attack when dead (0 life points).
	 * Uses weapon if life form has one, otherwise uses attack strength.
	 * @author Joshua Varone - modified to work with weapons
	 * @param target the life form being attacked
	 */
	public void attack(LifeForm target)
	{
		if (currentLifePoints > 0)
		{
			int distance;
			try {
				distance = Environment.getWorldInstance(1, 1).getDistance(this, target);
			} catch (EnvironmentException e1) {
				distance = 500; //set it far enough that no damage can result
			}
			if(weapon != null && weapon.getCurrentAmmo() > 0) 
			{
				target.takeHit(weapon.getDamage(distance));
				try {
					weapon.fire();
				} catch (OutOfAmmoException e) {
					//this should never be called, but is here as a failsafe
					target.takeHit(attackStrength);
				}
			}
			else if(distance <= 5)
			{
				target.takeHit(attackStrength);
			}
		}
	}
	
	/**
	 * Update current round if acting as a TimeObserver.
	 * @param time Round sent from Timer object.
	 */
	@Override
	public void updateTime(int time)
	{
		round = time;
	}

	/**
	 * Get current weapon equipped by life form.
	 * @return equipped weapon
	 */
	public Weapon getWeapon() 
	{
		return weapon;
	}

	/**
	 * Set weapon to be equipped by life form.
	 * Passing in null "drops" the current weapon.
	 * @author Joshua Varone
	 * @param weapon weapon to equip
	 */
	public void setWeapon(Weapon weapon) 
	{
		if(this.weapon == null || weapon == null)
		{
			this.weapon = weapon;
		}
	}
	
	/**
	 * Drops the current weapon the lifeform is holding.
	 * Returns the dropped weapon and sets weapon to null
	 * @author Josh Varone
	 */
	public Weapon dropWeapon()
	{
		if(weapon != null)
		{
			Weapon tempWp = weapon;
			weapon = null;
			return tempWp;
		}
		return null;
	}
	
	/**
	 * Changes the direction the LifeForm is facing
	 * @author Josh Varone
	 */
	public boolean changeDirection(String dir)
	{
		if(dir.equalsIgnoreCase("North"))
		{
			currentDirection = "North";
			return true;
		}
		else if(dir.equalsIgnoreCase("East"))
		{
			currentDirection = "East";
			return true;
		}
		else if(dir.equalsIgnoreCase("South"))
		{
			currentDirection = "South";
			return true;
		}
		else if(dir.equalsIgnoreCase("West"))
		{
			currentDirection = "West";
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the current direction of the LifeForm
	 * @author Josh Varone
	 * @return current direction of LifeForm
	 */
	public String getDirection()
	{
		return currentDirection;
	}
	
	/**
	 * Sets the max speed of the LifeForm (meaning the max number of spaces
	 * the LifeForm can move each round), as long as speed is >= 0
	 * @author Josh Varone
	 */
	public boolean setMaxSpeed(int speed)
	{
		if(speed >= 0)
		{
			maxSpeed = speed;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the max speed of the LifeForm
	 * @author Josh Varone
	 * @return max speed of the LifeForm
	 */
	public int getMaxSpeed()
	{
		return maxSpeed;
	}	
}
