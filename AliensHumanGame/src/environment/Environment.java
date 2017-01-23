package environment;
import exceptions.EnvironmentException;
import weapon.Weapon;
import lifeform.LifeForm;

/**
 * An environment composed of a two-dimensional grid of cells.
 * @author David Reichard
 * @author Josh Varone - added Lab 5 Singleton functionality
 * @author Jeff Titanich - added getters for World dimensions in Lab 6
 */
public class Environment {
	
	private Cell[][] cells;					//array of cells in environment
	private int rows, columns;				//rows and cells of environment
	private volatile static Environment e;	//unique instance of Environment
	private int selectedLFRow = 0;			//Beginning LifeForm starts at Row[0]
	private int selectedLFCol = 0;			//Beginning LifeForm starts at Col[0]
	
	/**
	 * Create an instance of an environment.
	 * @param rows Number of rows in environment.
	 * @param columns Number of columns in environment.
	 */
	private Environment(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		cells = new Cell[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				cells[i][j] = new Cell();
	}
	
	/**
	 * Return the life form in cell at specified position.
	 * @param row Row position of cell.
	 * @param column Column position of cell.
	 * @return Life form contained in cell at position row,column.
	 */
	public LifeForm getLifeForm(int row, int column)
	{
		if (row >= rows || column >= columns || row < 0 || column < 0)
			return null; // position is invalid.
		return cells[row][column].getLifeForm();
	}

	/**
	 * Add a life form to the environment at the specified location.
	 * @param entity Entity being added to the environment.
	 * @param row Row position of cell in environment to add entity.
	 * @param column Column position of cell in environment to add entity.
	 * @return True if successful, false if otherwise.
	 */
	public boolean addLifeForm(LifeForm entity, int row, int column) 
	{
		if (row >= rows || column >= columns || row < 0 || column < 0)
			return false; // position is invalid
		return cells[row][column].addLifeForm(entity);	
	}
	
	/**
	 * Remove a life form from the environment at the specified location.
	 * @param row Row position of cell in environment to remove entity.
	 * @param column Column position of cell in environment to remove entity.
	 * @return True if successful, false if otherwise.
	 */
	public boolean removeLifeForm(int row, int column) 
	{
		if (row < 0 || column < 0 || row >= rows || column >= columns)
			return false; // position is invalid
		return cells[row][column].removeLifeForm();		
	}
	
	/**
	 * If an instance of Environment does not already exist, create one.
	 * @return the instance of Environment.
	 * @author Josh Varone
	 */
	public static Environment getWorldInstance(int width, int height)
	{
		//check if an instance exists
		if(e == null)
			//if not, synchronize to prevent duplicate instantiation
			synchronized(Environment.class) {
				//if an instance still doesn't exits, make a new one
				if(e == null)
					e = new Environment(width, height);
			}
		//return the instance of Environment
		return e;
	}
	
	public static synchronized Environment getWorldInstance() 
	{
		if (e == null)
			e = new Environment(10, 10);
		return e;
	}

	
	
	/**
	 * Adds the specified weapon to the cell at (row, col).
	 * @return true if successfully added, false otherwise.
	 * @author Josh Varone
	 */
	public boolean addWeapon(Weapon wp, int row, int col)
	{
		if(row < rows && row >= 0 && col < columns && col >= 0)
			return cells[row][col].addWeapon(wp);
		return false;
	}
	
	/**
	 * Removes the weapon at the given position from the cell at (row, col).
	 * @return the removed weapon, or null if one was not removed.
	 * @author Josh Varone
	 */
	public Weapon removeWeapon(int position, int row, int col)
	{
		if(row < rows && row >= 0 && col < columns && col >= 0)
			return cells[row][col].removeWeapon(position);
		return null;
	}
	
	/**
	 * Gets the weapon at the specified position in the cell at (row, col).
	 * @return the weapon at the given position.
	 * @author Josh Varone
	 */
	public Weapon getWeapon(int position, int row, int col)
	{
		if(row < rows && row >= 0 && col < columns && col >= 0)
			return cells[row][col].getWeapon(position);
		return null;	
	}
	
	/**
	 * Calculates the distance between two given Cells within the environment.
	 * @return the distance between the two Cells
	 * @throws EnvironmentException if coordinates are out of bounds
	 * @author Josh Varone
	 */
	public int getDistance(int row1, int col1, int row2, int col2) throws EnvironmentException
	{
		if(row1 < rows && row2 < rows && col1 < columns && col2 < columns &&
				row1 >= 0 && row2 >= 0 && col1 >= 0 && col2 >= 0)
		{
			//if same row, return the absolute value of column difference times 5
			if(row1 == row2)
			{
				return Math.abs(col1-col2)*5;
			}
			//if same col, return the absolute value of row difference times 5
			else if(col1 == col2)
			{
				return Math.abs(row1-row2)*5;
			}
			//if different rows and columns, use Pythagorean Theorem to compute distance
			return (int)Math.sqrt(Math.pow((row1-row2)*5, 2)+Math.pow((col1-col2)*5, 2));	
		}
		else
		{
			throw new EnvironmentException();
		}
	}
	
	/**
	 * Calculates the distance between two given LifeForms within the environment.
	 * @return the distance between the two LifeForms
	 * @throws EnvironmentException if coordinates are out of bounds
	 * @author Josh Varone
	 */
	public int getDistance(LifeForm lf1, LifeForm lf2) throws EnvironmentException
	{
		int lf1Row = getLifeFormRow(lf1); //row of first lifeform
		int lf1Col = getLifeFormCol(lf1); //column of first lifeform
		int lf2Row = getLifeFormRow(lf2); //row of second lifeform
		int lf2Col = getLifeFormCol(lf2); //column of second lifeform
		
		//call getDistance(int row1, int col1, int row2, int col2) with LifeForm coordinates
		return getDistance(lf1Row, lf1Col, lf2Row, lf2Col);
	}
	
	/**
	 * Locates the specified lifeform in the Environment and returns the row.
	 * @param lf the lifeform being located
	 * @return the row of the lifeform, or -1 if not in the Environment
	 * @author Josh Varone
	 */
	private int getLifeFormRow(LifeForm lf)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				if(cells[i][j].getLifeForm() == lf)
					return i;
		return -1;
	}
	
	/**
	 * Locates the specified lifeform in the Environment and returns the column.
	 * @param lf the lifeform being located
	 * @return the column of the lifeform, or -1 if not in the Environment
	 * @author Josh Varone
	 */
	private int getLifeFormCol(LifeForm lf)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				if(cells[i][j].getLifeForm() == lf)
					return j;
		return -1;
	}
	
	/**
	 * Makes the Environment null so that it can be made new for tests.
	 * @author Josh Varone
	 */
	public void clearEnvironment()
	{
		Environment.e = null;
	}
	
	/**
	 * Moves the specified LifeForm maxSpeed spaces in its currentDirection,
	 * assuming no obstacles sit in the destination.
	 * @returns true if successfully moved, false otherwise
	 * @author Josh Varone
	 */
	public boolean move(LifeForm lf, int spaces)
	{
		int lfRow = getLifeFormRow(lf);
		int lfCol = getLifeFormCol(lf);
		if(lfRow >= 0 && lfCol >= 0)
			return move(lfRow, lfCol, spaces);
		return false;
	}
	
	/**
	 * Moves the LifeForm in the specified location the input number of spaces in
	 * the LifeForm's currentDirection, assuming no obstacles at the destination.
	 * @returns true if successfully moved, false otherwise
	 * @author Josh Varone
	 */
	public boolean move(int row, int col, int spaces)
	{
		LifeForm entity = cells[row][col].getLifeForm();
		//If no LifeForm at that location, return false
		if(entity == null || spaces <= 0)
			return false;
		//Cap the maximum number of spaces at the entity's maxSpeed
		if(spaces > entity.getMaxSpeed())
			spaces = entity.getMaxSpeed();
		
		if(entity.getDirection().equals("North")) {
			//If row-spaces would be negative, set spaces to row so row-spaces = 0
			if((row-spaces) < 0)
				spaces = row;
			//This cycles through the spaces in the path of the LifeForm's move,
			//going until it finds an available cell for the LifeForm to move to
			for(int i = 0; i < spaces; i++) {
				if(cells[row-(spaces-i)][col].getLifeForm() == null) {
					cells[row][col].removeLifeForm();
					cells[row-(spaces-i)][col].addLifeForm(entity);
					selectedLFRow = (row-(spaces-i));
					selectedLFCol = col;
					return true;
				}
			}
		}
		else if(entity.getDirection().equals("East")) {
			//If col+spaces exceeds columns, set spaces so col+spaces is the last col
			if((col+spaces) >= columns)
				spaces = (columns - col) - 1;
			//This cycles through the spaces in the path of the LifeForm's move,
			//going until it finds an available cell for the LifeForm to move to
			for(int i = 0; i < spaces; i++) {
				if(cells[row][col+(spaces-i)].getLifeForm() == null) {
					cells[row][col].removeLifeForm();
					cells[row][col+(spaces-i)].addLifeForm(entity);
					selectedLFRow = row;
					selectedLFCol = (col+(spaces-i));
					return true;
				}
			}
		}
		else if(entity.getDirection().equals("South")) {
			//If row+spaces exceeds rows, set spaces so that row+spaces is the last row
			if((row+spaces) >= rows)
				spaces = (rows - row) - 1;
			//This cycles through the spaces in the path of the LifeForm's move,
			//going until it finds an available cell for the LifeForm to move to
			for(int i = 0; i < spaces; i++) {
				if(cells[row+(spaces-i)][col].getLifeForm() == null) {
					cells[row][col].removeLifeForm();
					cells[row+(spaces-i)][col].addLifeForm(entity);
					selectedLFRow = (row+(spaces-i));
					selectedLFCol = col;
					return true;
				}
			}
		}
		else if(entity.getDirection().equals("West")) {
			//If col-spaces would be negative, set spaces to col so col-spaces = 0
			if((col-spaces) < 0)
				spaces = col;
			//This cycles through the spaces in the path of the LifeForm's move,
			//going until it finds an available cell for the LifeForm to move to
			for(int i = 0; i < spaces; i++) {
				if(cells[row][col-(spaces-i)].getLifeForm() == null) {
					cells[row][col].removeLifeForm();
					cells[row][col-(spaces-i)].addLifeForm(entity);
					selectedLFRow = row;
					selectedLFCol = (col-(spaces-i));
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return the number of rows in the Environment
	 */
	public int getWorldRows()
	{
		return rows;
	}
	
	/**
	 * @return the number of columns in the Environment
	 */
	public int getWorldCols()
	{
		return columns;
	}
	
	
	/**
	 * Returns the currently selected LifeForm's row position.
	 * @author Jordan Long
	 * @return selectedLFRow Current Selected LifeForm's row position.
	 */
	public int getselectedLFRow()
	{
		return selectedLFRow;
	}
	
	/**
	 * Returns the currently selected LifeForm's column position.
	 * @author Jordan Long
	 * @return selectedLFCol Current Selected LifeForm's column position.
	 */
	public int getselectedLFCol()
	{
		return selectedLFCol;
	}
	
	
}
