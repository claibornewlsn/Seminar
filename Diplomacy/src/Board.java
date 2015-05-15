import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class, board, acts as the data holding device for all of Diplomacy.  The board
 * is a 2D array of ints which starts as all 0s.  0 represents a blank space, 1 represents
 * a dot, and all numbers >=2 represent different players (for example, player 1 is represented by a 2)
 * The field SIZE is a constant that 
 * determines the size of the array and therefore the GUI representation of the board
 * This class also has a field "dots" which is an arrayList containing all of the dots in the board
 * 
 * @author wilsonc
 *
 */
public class Board {
	private static final int SIZE = 15;
	private final int PLAYER1 = 2;
	private final int PLAYER2 = 3;
	private ArrayList<Dot> dots = new ArrayList<Dot>();
	private Player p1;
	private Player p2;
	//for strategy:
	
	
	private int[][] board = new int[SIZE][SIZE];
	
	//constructor for Board, creates the board as all 0s and then adds in 2 players and dots
	public Board(){
		for(int c=0; c<board.length; c++){
			for(int r=0; r<board.length; r++){
				board[r][c] = 0;
			}
		}
		// add dot 1 randomly
		int row = (int) (Math.random() * SIZE);
		int col = (int) (Math.random() * SIZE);
		addDot(row, col);
		dots.add(new Dot(row, col, 1));
		
		//add dot 2 randomly
		int row2 = (int) (Math.random() * 5);
		int col2 = (int) (Math.random() * 5);
		while (row == row2 && col == col2) {
			row2 = (int) (Math.random() * 5);
			col2 = (int) (Math.random() * 5);
		}

		addDot(row2, col2);
		dots.add(new Dot(row2, col2, 1));
		
		//add dot 3 randomly
		int row3 = (int) (Math.random() * 5);
		int col3 = (int) (Math.random() * 5);
		//have to check that the location of this dot is not the same as any other dot....
		while ((row3 == row2 && col3 == col2) || (row3 == row && col3 == col)) {
			row3 = (int) (Math.random() * 5);
			col3 = (int) (Math.random() * 5);
		}
		
		addDot(row3, col3);
		dots.add(new Dot(row3, col3, 1));
		
//		row3 = (int) (Math.random() * 5);
//		col3 = (int) (Math.random() * 5);
//		addDot(row3, col3);
//		dots.add(new Dot(row3, col3, 1));
//		
//		row3 = (int) (Math.random() * 5);
//		col3 = (int) (Math.random() * 5);
//		addDot(row3, col3);
//		dots.add(new Dot(row3, col3, 1));
//		
//		row3 = (int) (Math.random() * 5);
//		col3 = (int) (Math.random() * 5);
//		addDot(row3, col3);
//		dots.add(new Dot(row3, col3, 1));
//		
//		row3 = (int) (Math.random() * 5);
//		col3 = (int) (Math.random() * 5);
//		addDot(row3, col3);
//		dots.add(new Dot(row3, col3, 1));
		
		
		// add players randomly
		row = (int) (Math.random() * SIZE);
		col = (int) (Math.random() * SIZE);
		row2 = (int) (Math.random() * SIZE);
		col2 = (int) (Math.random() * SIZE);
		
		
		//before creating the players, confirm they are not on the same location as eachother or as a dot
		boolean go = true;
		while(go){
			for(Dot d : dots){
				go = false;
				if(row == d.getRow() && col == d.getCol()){
					row = (int) (Math.random() * SIZE);
					col = (int) (Math.random() * SIZE);
					go = true;
				}
				if(row2 == d.getRow() && col2 == d.getCol()){
					row2 = (int) (Math.random() * SIZE);
					col2 = (int) (Math.random() * SIZE);
					go = true;
				}
				if(row == row2 && col == col2){
					row2 = (int) (Math.random() * SIZE);
					col2 = (int) (Math.random() * SIZE);
					go = true;
				}
			}
		}
		
		//create players 1 and 2
		p1 = new Player(2, row, col);
		board[row][col] = PLAYER1;

		p2 = new Player(3, row2, col2);
		board[row2][col2] = PLAYER2;
	
	}
	
	//simple accesser  method for retrieving the value at row r and column c
	public int get (int r, int c){
		return board[r][c];
	}
	
	//adds a dot to board at board[r][c]
	private void addDot(int r, int c){
		board[r][c] = 1;
		//dots are represented by 1
	}
	
	//returns true if the value at r, c is 1
	private boolean isDot(int r, int c) {
		return board[r][c] == 1;
	}
	
	private boolean isPlayer(int r, int c) {
		return (board[r][c] != 0 && !isDot(r,c));
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	//the move method is called by the GUI class, the calls to "changeClosestDotLoc" refer to the
	//player class.  
	public void move(){
		p1.changeClosestDotLoc(dots);
		p2.changeClosestDotLoc(dots);
		
		//System.out.println("P1 " + p1.getClosestDotR() + " , " + p1.getClosestDotC() + " from " + p1.getRow() + " , " + p1.getCol());
		//System.out.println("P2 " + p2.getClosestDotR() + " , " + p2.getClosestDotC() + " from " + p2.getRow() + " , " + p2.getCol());
		
		System.out.println("1: " + p1.getRow() + " , " + p1.getCol());
		
		
		//When moving a player, the location it used to be at is set to 0.  After all the moves have been made,
		//the arraylist dots is gone through and the dots are added back.  The calls "determineOffOrDef" 
		//first decide whether an offinsive or devensive strategy is prefereable and then trigger 
		//calls to actually move the player
		board[p1.getRow()][p1.getCol()] = 0;
		p1.determineOffOrDef(p2);
		System.out.println("2: " + p1.getRow() + " , " + p1.getCol());
		board[p1.getRow()][p1.getCol()] = PLAYER1;
		
	
		
		board[p2.getRow()][p2.getCol()] = 0;
		
		//something is wrong here^^^^^^^^^^^^^
		//If a player goes out of bounds, the error shows up here.
		
		p2.determineOffOrDef(p1);
		System.out.println("3: " + p1.getRow() + " , " + p1.getCol());
		board[p2.getRow()][p2.getCol()] = PLAYER2;
		
		for(Dot d : dots){
			if(p1.getRow() == d.getRow() && p1.getCol() == d.getCol()){
				d.setPlayer(PLAYER1);
			}
			else if(p2.getRow() == d.getRow() && p2.getCol() == d.getCol()){
				d.setPlayer(PLAYER2);
			}
		}


		
	}

	//after each move, the dots are replaced to ensure they are not lost.  This is done because
	//as a player moves, it changes it's previous location to 0
	public void replaceDots(){
		for(Dot d : dots){
			if(board[d.getRow()][d.getCol()] == 0)
				board[d.getRow()][d.getCol()] = 1;
			System.out.println("Dot (" + d.getRow() + "," + d.getCol() + ") owned by: " + d.getPlayer());
		}
	}

	
//
//	public void moveUp (Player p){
//		board[p.getRow()][p.getCol()] = 0;
//		p.setRow(p.getRow()-1);
//		
//	}
//	
//	public void moveDown (Player p){
//		p.setRow(p.getRow() +1);
//		
//	}
//	
//	public void moveRight (Player p){
//		p.setCol(p.getCol() + 1);
//		
//	}
//	
//	public void moveLeft (Player p){
//		p.setCol(p.getCol() -1);
//		
//	}
	
	public static int getSize(){
		return SIZE;
	}
	
	public void setCell(int r, int c, int v){
		board[r][c] = v;
	}
	
	public void print(Board b) {
		for (int r = 0; r < SIZE; r++) {
			System.out.println();
			for (int c = 0; c < SIZE; c++) {
				System.out.print("" + b.board[r][c] + " ");
			}
		}
	}

	
}
