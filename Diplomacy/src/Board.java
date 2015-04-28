import java.awt.Color;

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
//	public void addPlayer(int r, int c, int num) {
//		if(PLAYER1 == num){
//			board[r][c] = p1.getColor();
//		}
//		else if (PLAYER2 == num){
//			board[r][c] = p2.getColor();
//		}
//	}
	public void removePlayer(int r, int c) {
		board[r][c] = 0;
	}
	
	
	public int[][] getBoard() {
		return board;
	}
	
	public void move(Player p) {
		p.movePlayerOff();
	}
	public void moveUp (Player p){
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
	
	
	
	public void print(Board b) {
		for (int r = 0; r < SIZE; r++) {
			System.out.println();
			for (int c = 0; c < SIZE; c++) {
				System.out.print("" + b.board[r][c] + " ");
			}
		}
	}
	

	
}
