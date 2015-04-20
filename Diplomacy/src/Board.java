import java.awt.Color;

// COLE OTTO IS THE BESTEST
public class Board {
	private static final int SIZE = 10;
	private final int PLAYER1 = 2;
	private final int PLAYER2 = 3;
	static Player p1;
	static Player p2;
	
	private static Color[][] board = new Color[SIZE][SIZE];
	
	public Board(){
		for(int c=0; c<board.length; c++){
			for(int r=0; r<board.length; r++){
				board[r][c] = Color.WHITE;
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
		addPlayer(row,col,PLAYER1);

		row2 = (int) (Math.random() * 5);
		col2 = (int) (Math.random() * 5);

		while (row == row2 && col == col2) {
			row2 = (int) (Math.random() * 5);
			col2 = (int) (Math.random() * 5);
		}

		row2 = (int) (Math.random() * SIZE);
		col2 = (int) (Math.random() * SIZE);
		addPlayer(row2,col2,PLAYER2);

		p1 = new Player(PLAYER1, Color.RED);
		p2 = new Player(PLAYER2, Color.BLUE);

		
		
	}
	private void addDot(int r, int c){
		board[r][c] = Color.BLACK;
		//dots are represented by 1
	}
	private boolean isDot(int r, int c) {
		return board[r][c] == Color.BLACK;
	}
	private boolean isPlayer(int r, int c) {
		return (board[r][c] != Color.WHITE && !isDot(r,c));
	}
	public void addPlayer(int r, int c, int num) {
		if(PLAYER1 == num){
			board[r][c] = p1.getColor();
		}
		else if (PLAYER2 == num){
			board[r][c] = p2.getColor();
		}
	}
	public void removePlayer(int r, int c) {
		board[r][c] = Color.WHITE;
	}
	
	
	public static Color[][] getBoard() {
		return board;
	}
	
	public static void move(Player p) {
		p.movePlayerOff();
		board = p.getBoard();
	}
	
	public static int getSize(){
		return SIZE;
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
		for (int i = 0; i < 5; i++) {
			move(p1);
			move(p2);
			System.out.println();
			print(b);
		}
		
	}
	
}
