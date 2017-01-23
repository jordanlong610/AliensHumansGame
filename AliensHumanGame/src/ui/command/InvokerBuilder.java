package ui.command;

import environment.Environment;

public class InvokerBuilder
{
/**
 * InvokerBuilder
 * @author Jordan Long
 */
	Environment environment = Environment.getWorldInstance();

	
	/**
	 * Constructor for Invoker Builder
	 * @author Jordan Long
	 */
	public InvokerBuilder()
	{
		Invoker.setCommand(0, new Acquire(environment));
		Invoker.setCommand(1, new Attack(environment));
		Invoker.setCommand(2, new Drop(environment));
		Invoker.setCommand(3, new Move(environment));
		Invoker.setCommand(4, new Reload(environment));
		Invoker.setCommand(5, new turnEast(environment));
		Invoker.setCommand(6, new turnNorth(environment));
		Invoker.setCommand(7, new turnSouth(environment));
		Invoker.setCommand(8, new turnWest(environment));
		
		

		
		
	}
	

	
}
