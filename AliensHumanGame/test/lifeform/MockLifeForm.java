package lifeform;

/**
 * Mock life form used for testing the abstract class LifeForm.
 * @author David Reichard
 * @author Josh Varone - Command Lab change
 *
 */
public class MockLifeForm extends LifeForm 
{
	public MockLifeForm(String name, int points)
	{
		super(name, points);
		maxSpeed = 3;
	}
	
	public int getRound()
	{
		return round;
	}
}