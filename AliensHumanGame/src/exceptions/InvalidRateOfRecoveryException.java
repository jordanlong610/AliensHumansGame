package exceptions;

@SuppressWarnings("serial")
public class InvalidRateOfRecoveryException extends Exception
{
	private int rate;
	
	public InvalidRateOfRecoveryException(int rate)
	{
		this.rate = rate;
	}
	
	public int getRate()
	{
		return rate;
	}
}
