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
		int[] p1Temp = p1.moveOffense(board, SIZE);
		//returns array in the form [rowValue][colValue}
		
		board[p1.getRow()][p1.getCol()] = 0;
		p1.movePlayerOff();
		board[p1.getRow()][p1.getCol()] = PLAYER1;
		
		//need to set the value in board
		
		int[] p2Temp = p2.moveOffense(board, SIZE);
		board[p2.getRow()][p2.getCol()] = 0;
		p2.movePlayerOff();
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
