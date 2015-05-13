import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// COLE OTTO IS THE BESTEST
//strategy and player movement goes here (board)
//create new class for movement
//canvas takes the board and creates the color representation, checks if controls are right
//GUI presents the life canvas (gets run)
public class Board {
	private static final int SIZE = 10;
	private final int PLAYER1 = 2;
	private final int PLAYER2 = 3;
	private ArrayList<Dot> dots = new ArrayList<Dot>();
	private Player p1;
	private Player p2;
	//for strategy:
	
	
	private int[][] board = new int[SIZE][SIZE];
	
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
		
		p1 = new Player(2, row, col);
		board[row][col] = PLAYER1;

		p2 = new Player(3, row2, col2);
		board[row2][col2] = PLAYER2;
	
	}
	public int get (int r, int c){
		return board[r][c];
	}
	
	private void addDot(int r, int c){
		board[r][c] = 1;
		//dots are represented by 1
	}
	private boolean isDot(int r, int c) {
		return board[r][c] == 1;
	}
	private boolean isPlayer(int r, int c) {
		return (board[r][c] != 0 && !isDot(r,c));
	}
	public int[][] getBoard() {
		return board;
	}
	
	public void move(){
		p1.changeClosestDotLoc(dots);
		p2.changeClosestDotLoc(dots);
		
		//System.out.println("P1 " + p1.getClosestDotR() + " , " + p1.getClosestDotC() + " from " + p1.getRow() + " , " + p1.getCol());
		//System.out.println("P2 " + p2.getClosestDotR() + " , " + p2.getClosestDotC() + " from " + p2.getRow() + " , " + p2.getCol());
		
		System.out.println("1: " + p1.getRow() + " , " + p1.getCol());
		
		
		board[p1.getRow()][p1.getCol()] = 0;
		p1.determineOffOrDef(p2);
		System.out.println("2: " + p1.getRow() + " , " + p1.getCol());
		board[p1.getRow()][p1.getCol()] = PLAYER1;
		
		//need to set the value in board
		
		
		board[p2.getRow()][p2.getCol()] = 0;
		
		//something is wrong here^^^^^^^^^^^^^
		
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
