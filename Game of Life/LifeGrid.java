
public class LifeGrid
{
	/**
	 * @author Cole Otto
	 * Date of Completion 9.28.14
	 * Assignment: Create a working Game of Life
	 * Attribution: Group brainstorm
	 * Honor Pledge: All Work Here Is Honestly Obtained And Is My Own! 
	 */
	
	
	
	// creates the grid itself
	private int[][] grid;
	
	//Constants - values that a cell could be at any given time
	
	final int DD =0;   // DD - the cell was dead last turn and still is
	final int AA =1;   // AA - the cell was alive last turn and still is
	final int DA =2;   // DA - the cell was dead last turn and is now alive
	final int AD =3;   // AD - the cell was alive last turn and is now dead
	
	
	
	
	//Constructors
	
	//lifegrid creation
	public LifeGrid() {
		grid = new int[50][50];  // makes the grid 50 x 50 
	}
	public LifeGrid(int r, int c) {   // Overloaded constructor
		grid = new int[r][c];    // makes the grid r rows and c columns 
	}
    
	
	/**
	 *  get cell x,y
	 * @param r  An int which is the row value of a cell
	 * @param c  An int which is the column value of a cell
	 * @return 1 if the value at that cell is alive and 0 if it is dead
	 */
	public int getCell(int r, int c) {
		if (grid[r][c] == AA || grid[r][c] == DA)
			return 1;
		else
			return 0;	
	}
	
	/** sets the value of a certain cell
	 * 
	 * @param r An int which is the row value of a cell
	 * @param c An int which is the column value of a cell
	 * @param v An int value that the cell is changed to
	 */
    public void setCell(int r, int c, int v) {
    	grid[r][c] = v;
    }
    
    
	/** finds the number of rows in the grid
	 * @return the number of rows
	 */
    public int getNumRows() {
    	return grid.length;
    }
    
	
	/** finds the number of columns in the grid
	 * @return the number of columns
	 */
    public int getNumCols() {
    	return grid[0].length;
    }
    	
	
	/** finds how many neighbors a cell has
	 * 
	 * @param row The row value at the cell
	 * @param col The column value at the cell
	 * @return number of neighbors the cell has
	 */
    private int countNeighbors(int row, int col) {
    	int count = 0;
    	for (int r = row-1; r <= row+1; r++)
    		for (int c = col-1; c <= col+1; c++)   // goes to every row and column in a three by three around it (including itself)
    			if (r>=0 && r<=grid.length-1 && c>=0 && c<=grid[r].length-1) // makes sure the cell is within the boundaries
    				if (grid[r][c] == AA || grid[r][c] == AD) // if the cell was alive last turn, increase count
    				count++;
    	if(grid[row][col] == AA || grid[row][col] == AD) // since one of the cells it checked was the cell itself, it checks to see if that cell was alive
    		count--;                                     // in which case, it decreases the count
    	return count;
    }
    
    
    /** changes the state of one cell
     * 
     * @param row The row value at the cell
	 * @param col The column value at the cell
	 * 
	 * if the cell was alive and has 2-3 neighbors, it will stay alive
	 * if the cell was alive and does not have 2-3 neighbors, it will become dead
	 * if the cell was dead and has 3 neighbors, it will become alive
	 * if the cell was dead and does not have 3 neighbors, it will stay dead
     */
    private void evolveCell(int row, int col) {
    	int numNeighbors = countNeighbors(row, col);
    	if (grid[row][col] == AA && (numNeighbors == 2 || numNeighbors == 3)) 
    		grid[row][col] = AA;
    	if (grid[row][col] == AA  && (numNeighbors != 2 && numNeighbors != 3))
    		grid[row][col] = AD;
    	if (grid[row][col] == DD && numNeighbors == 3)
    		grid[row][col] = DA;
    	if (grid[row][col] == DD && numNeighbors != 3)
    		grid[row][col] = DD;   	
    }
    
    /** evolves all the cells
     *  checks every cell and evolves them
     *  changes all the cells to either alive or dead
     */
    public void evolve(){
    	for (int r = 0; r < grid.length; r++)
    		for (int c = 0; c < grid[r].length; c++)
    			evolveCell(r,c);
    	for (int r = 0; r < grid.length; r++)
    		for (int c = 0; c < grid[r].length; c++){
    			if (grid[r][c] == AD)
    				grid[r][c] =DD;
    			if (grid[r][c] == DA)
    				grid[r][c] =AA;
    		}
    			
    }
    
}
