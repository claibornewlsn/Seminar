import java.awt.Color;
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
	Player p1;
	Player p2;
	//for strategy:
	
	
	private int[][] board = new int[SIZE][SIZE];
	
	public Board(){
		for(int c=0; c<board.length; c++){
			for(int r=0; r<board.length; r++){
				board[r][c] = 0;
			}
		}
		// add dots randomly
		int row = (int) (Math.random() * SIZE);
		int col = (int) (Math.random() * SIZE);
		addDot(row, col);
		int row2 = (int) (Math.random() * 5);
		int col2 = (int) (Math.random() * 5);
		while (row == row2 && col == col2) {
			row2 = (int) (Math.random() * 5);
			col2 = (int) (Math.random() * 5);
		}

		addDot(row2, col2);
		
		// add players randomly
		row = (int) (Math.random() * SIZE);
		col = (int) (Math.random() * SIZE);
		p1 = new Player(2, row, col);
		board[row][col] = PLAYER1;


		row2 = (int) (Math.random() * SIZE);
		col2 = (int) (Math.random() * SIZE);

		while (row == row2 && col == col2) {
			row2 = (int) (Math.random() * SIZE);
			col2 = (int) (Math.random() * SIZE);
		}

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
		p1.setRow(p1Temp[0]);
		p1.setCol(p1Temp[1]);
		board[p1.getRow()][p1.getCol()] = PLAYER1;
		
		//need to set the value in board
		
		int[] p2Temp = p2.moveOffense(board, SIZE);
		board[p2.getRow()][p2.getCol()] = 0;
		p2.setRow(p2Temp[0]);
		p2.setCol(p2Temp[1]);	
		board[p2.getRow()][p2.getCol()] = PLAYER2;
	}

	

	public void moveUp (Player p){
		board[p.getRow()][p.getCol()] = 0;
		p.setRow(p.getRow()-1);
	}
	
	public void moveDown (Player p){
		p.setRow(p.getRow() +1);
	}
	
	public void moveRight (Player p){
		p.setCol(p.getCol() + 1);
	}
	
	public void moveLeft (Player p){
		p.setCol(p.getCol() -1);
	}
	
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
