package ui.command;

public abstract class Invoker
{
/**
 * Invoker
 * When a button is pressed, parameters will be passed here and the appropriate command will be invoked.
 * 
 * @author Jordan Long
 */
	
	
	
	/**
	 * Creates the array of commands.
	 */
	private static Command[] commands = new Command[9];
	
	
	/**
	 * Adds command to specified slot in command array.
	 * @param slot
	 * @param command
	 */
	public static  void setCommand(int slot, Command command)
	{
		commands[slot] = command;
	}
	
	
	/**
	 * Executes the command that is called from the GUI.
	 * @param slot
	 * @param row
	 * @param col
	 */
	  public static void pressButton(int slot, int row, int col)
	  {
		  commands[slot].execute(row, col);
	  }
	
}
