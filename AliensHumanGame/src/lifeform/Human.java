package lifeform;

/**
 * A human is a type of life form with armor.
 * @author David Reichard
 * @author Josh Varone - updated maxSpeed
 *
 */
public class Human extends LifeForm 
{
	private int armorPoints = 0;
	
	/**
	 * Initialize the human with default armor value of 0.
	 * @param name Name of the human.
	 * @param life Beginning life points of human.
	 */
	public Human(String name, int life)
	{
		this(name, life, 0);
	}
	
	/**
	 * Initialize the human.
	 * @param name Name of the human.
	 * @param life Beginning life points of human.
	 * @param armor Beginning armor points of human.
	 */
	public Human(String name, int life, int armor)
	{
		super(name, life);
		if (armor >= 0)
			armorPoints = armor;
		this.setAttackStrength(5);
		maxSpeed = 3;
	}

	/**
	 * Get current armor points of human.
	 * @return current armor points of human.
	 */
	public int getArmorPoints() 
	{
		return armorPoints;
	}

	/**
	 * Set current armor points of human.
	 * @param armor Amount of armor points to set.
	 */
	public void setArmorPoints(int armor)
	{
		if (armor < 0)
			armorPoints = 0;
		else
			armorPoints = armor;
	}
	
	/**
	 * Override of LifeForm's takeHit() method. 
	 * Will absorb damage based on armor rating
	 */
	@Override
	public void takeHit(int damage)
	{
		int total = damage - armorPoints;
		if (total < 0)
			total = 0;
		super.takeHit(total);
	}
}
