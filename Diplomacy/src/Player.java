import java.util.Queue;


public class Player {

	private int playerR;
	private int playerC;
	private int playerValue;
	private int[][] board;
	// *** Offensive Strategy ***
	private int closestDotR;
	private int closestDotC;
	private int closestDotDis;
	private Queue<Integer> dotR;
	private Queue<Integer> dotC;
	
	
	public Player(int val, int[][] board) {
		playerValue = val;
		this.board = board;
		makeOffense();
	}
	
	
	// *** Offensive Strategy ***
	private void makeOffense() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c] == playerValue) {
					playerR = r;
					playerC = c;
				}
				if (board[r][c] == 1) {
					dotR.add(r);
					dotC.add(c);
				}
			}
		}
		findClosestDot();
	}
	
	private void findClosestDot() {
		closestDotR = dotR.poll();
		closestDotC = dotC.poll();
		closestDotDis = dotDistance(closestDotR,closestDotC);
		while (!dotR.isEmpty() || !dotC.isEmpty()) {
			if (closestDotDis > dotDistance(dotR.peek(),dotC.peek())) {
				closestDotR = dotR.poll();
				closestDotC = dotC.poll();
				closestDotDis = dotDistance(closestDotR,closestDotC);
			}
		}
	}
	
	private int dotDistance(int r, int c) {
		int ans = Math.abs(playerR-r);
		ans += Math.abs(playerC-c);
		return ans;
	}
	// ***************************************
	
	private void moveUp() {
		
	}
	private void moveDown() {
		
	}
	private void moveLeft() {
		
	}
	private void moveRight() {
		
	}
	
	// moves the player using the offensive strategy
	public void movePlayerOff() {
		int tie = (int) (Math.random()*2);
		if (closestDotR < playerR) {
			if (closestDotC < playerC) {
				if (tie == 0) {
					moveUp();
				}
				else {
					moveLeft();
				}
			}
			else if (closestDotC > playerC) {
				if (tie == 0) {
					moveUp();
				}
				else {
					moveRight();
				}
			}
			else {
				moveUp();
			}
		}
		
	}
	
	
}
