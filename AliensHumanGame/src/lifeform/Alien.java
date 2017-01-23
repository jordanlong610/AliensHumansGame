package lifeform;

import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

import exceptions.InvalidRateOfRecoveryException;

/**
 * An alien is a type of life form which can recover hit points over time.
 * @author David Reichard
 * @author Josh Varone - updated maxSpeed
 *
 */
public class Alien extends LifeForm
{
	private int maxLifePoints;
	private RecoveryBehavior recoveryBehavior;
	private int recoveryRate;
	private int recoveryTimer;
	
	/**
	 * Initialize alien with default recovery behavior of none.
	 * @param name Name of alien.
	 * @param life Total starting life points.
	 * @throws InvalidRateOfRecoveryException 
	 */
	public Alien(String name, int life) throws InvalidRateOfRecoveryException
	{
		this(name, life, new RecoveryNone(), 0);
	}
	
	/**
	 * Initialize alien with a specific recovery behavior.
	 * @param name Name of alien.
	 * @param life Total starting life points.
	 * @param rb RecoveryBehavior object to use for recovery behavior.
	 * @param rate Rate of recovery measured in rounds. Smaller is better.
	 * @throws InvalidRateOfRecoveryException 
	 */
	public Alien(String name, int life, RecoveryBehavior rb, int rate) throws InvalidRateOfRecoveryException
	{
		super(name, life);
		maxLifePoints = life;
		recoveryBehavior = rb;
		this.setAttackStrength(10);
		this.setRecoveryRate(rate);
		maxSpeed = 2;
	}

	/**
	 * Sets the recovery rate. This is the amount of
	 * hit points that replenish each recovery cycle.
	 * @param rate Rate of recovery.
	 * @throws InvalidRateOfRecoveryException 
	 */
	public void setRecoveryRate(int rate) throws InvalidRateOfRecoveryException
	{
		if (rate < 0)
			throw new InvalidRateOfRecoveryException(rate);
		this.recoveryRate = rate;
	}
	
	/**
	 * Get the current recovery rate.
	 * @return Amount of hit points replenished during each recovery cycle.
	 */
	public int getRecoveryRate()
	{
		return recoveryRate;
	}
	
	/**
	 * Recover using alien's concrete recovery behavior.
	 */
	protected void recover() 
	{
		currentLifePoints = recoveryBehavior.calculateRecovery(currentLifePoints, maxLifePoints);
	}
	
	/**
	 * Override default updateTime() behavior of LifeForm 
	 * to include alien recovery behavior.
	 */
	@Override
	public void updateTime(int time)
	{
		super.updateTime(time);
		
		// Recovery Behavior
		if (recoveryRate > 0)
		{
			recoveryTimer++;
			if (recoveryTimer >= recoveryRate)
			{
				recoveryTimer = 0;
				recover();
			}
		}
	}
}
