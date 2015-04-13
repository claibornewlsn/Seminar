// COLE OTTO IS THE BESTEST
public class Board {
	private static final int SIZE = 6;
	private final int PLAYER1 = 2;
	private final int PLAYER2 = 3;
	
	private static int[][] board = new int[SIZE][SIZE];
	
	public Board(){
		for(int c=0; c<board.length; c++){
			for(int r=0; r<board.length; r++){
				board[r][c] = 0;
			}
		}
		// add dots randomly
		int row = (int) (Math.random() * 5);
		int col = (int) (Math.random() * 5);
		addDot(row, col);
		int row2 = (int) (Math.random() * 5);
		int col2 = (int) (Math.random() * 5);
		while (row == row2 && col == col2) {
			row2 = (int) (Math.random() * 5);
			col2 = (int) (Math.random() * 5);
		}
		addDot(row2, col2);
		// add players randomly
		row = (int) (Math.random() * 5);
		col = (int) (Math.random() * 5);
		addPlayer(row,col,PLAYER1);
		row2 = (int) (Math.random() * 5);
		col2 = (int) (Math.random() * 5);
		while (row == row2 && col == col2) {
			row2 = (int) (Math.random() * 5);
			col2 = (int) (Math.random() * 5);
		}
		addPlayer(row2,col2,PLAYER2);
		
		
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
	private void addPlayer(int r, int c, int num) {
		board[r][c] = num;
	}
	
	
	public int[][] getBoard() {
		return board;
	}
	
	public static void main(String args[]) {
		Board b = new Board();
		for (int r = 0; r < SIZE; r++) {
			System.out.println();
			for (int c = 0; c < SIZE; c++) {
				System.out.print("" + b.board[r][c] + " ");
			}
		}
	}
}
