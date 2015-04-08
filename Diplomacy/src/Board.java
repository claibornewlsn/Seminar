// COLE OTTO IS THE BESTEST
public class Board {
	private int[][] board = new int[5][5];
	
	public Board(){
		for(int c=0; c<board.length; c++){
			for(int r=0; r<board.length; r++){
				board[r][c] = 0;
			}
		}
		int row = Math.random() * 5;
		int col = Math.random() * 5;
		addDot(row, col);
	}
	public void addDot(int r, int c){
		board[r][c] = 1;
		//dots are represented by 1
	}
}
