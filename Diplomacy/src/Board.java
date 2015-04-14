// COLE OTTO IS THE BESTEST
public class Board {
	private static final int SIZE = 10;
	private final int PLAYER1 = 2;
	private final int PLAYER2 = 3;
	static Player p1;
	static Player p2;
	
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
		p1 = new Player(PLAYER1, board);
		p2 = new Player(PLAYER2, board);
		
		
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
	public void addPlayer(int r, int c, int num) {
		board[r][c] = num;
	}
	public void removePlayer(int r, int c) {
		board[r][c] = 0;
	}
	
	
	public int[][] getBoard() {
		return board;
	}
	
	public static void move(Player p) {
		p.movePlayerOff();
		board = p.getBoard();
	}
	
	private static void print(Board b) {
		for (int r = 0; r < SIZE; r++) {
			System.out.println();
			for (int c = 0; c < SIZE; c++) {
				System.out.print("" + b.board[r][c] + " ");
			}
		}
	}
	
	public static void main(String args[]) {
		Board b = new Board();
		print(b);
		move(p1);
		move(p2);
		System.out.println();
		print(b);move(p1);
		move(p2);
		System.out.println();
		print(b);
		move(p1);
		move(p2);
		System.out.println();
		print(b);
	}
	
}
