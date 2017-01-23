package weapon;

import org.junit.Test;

import exceptions.TooManyAttachmentsException;

/**
 * Test to make sure you cannot add more than two Attachments
 * 
 * @author James Hall
 *
 */
public class TestTooManyAttachments 
{
	/*******
	 * EXTRA CREDIT tests for Decorator Lab
	 *******/
	
	/**
	 * Check to make sure an exception is thrown when a third attachment is
	 * added
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test(expected = TooManyAttachmentsException.class)
	public void testTooManyAttachments() throws TooManyAttachmentsException 
	{
		new Stabilizer(new Scope(new PowerBooster(new Pistol())));
	}

}
