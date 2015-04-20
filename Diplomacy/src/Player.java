import java.awt.Color;
import java.awt.List;
import java.util.LinkedList;
import java.util.Queue;


public class Player {

	private int playerR;
	private int playerC;
	private Color playerColor;
	private int playerValue;
	private int[][] board;
	// *** Offensive Strategy ***
	private int closestDotR;
	private int closestDotC;
	private int closestDotDis;
	private Queue<Integer> dotR = new LinkedList<Integer>();
	private Queue<Integer> dotC = new LinkedList<Integer>();
	
	
	public Player(int val, Color color) {
		playerValue = val;
		int r = (int)(Math.random() * Board.getSize());
		playerR = r;
		int c = (int)(Math.random() * Board.getSize());
		playerC = c;
		playerColor = color;
		makeOffense();
	}
	
	
	// *** Offensive Strategy ***
	private void makeOffense() {
		int num = 0;
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
				System.out.println("In lop time: " + num);
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
			else{
				dotR.poll();
				dotC.poll();
			}

		}
	}
	
	private int dotDistance(int r, int c) {
		int ans = Math.abs(playerR-r);
		ans += Math.abs(playerC-c);
		return ans;
	}
	// ***************************************
	
	
	private void moveUp() {	//row--, col same
		board[playerR][playerC] = 0;
		playerR--;
		board[playerR][playerC] = playerValue;
	}
	private void moveDown() {
		board[playerR][playerC] = 0;
		playerR++;
		board[playerR][playerC] = playerValue;
	}
	private void moveLeft() {
		board[playerR][playerC] = 0;
		playerC--;
		board[playerR][playerC] = playerValue;
	}
	private void moveRight() {
		board[playerR][playerC] = 0;
		playerC++;
		board[playerR][playerC] = playerValue;
	}
	
	public Color[][] getBoard() {
		return board;
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
		else if (closestDotR > playerR) {
			if (closestDotC < playerC) {
				if (tie == 0) {
					moveDown();
				}
				else {
					moveLeft();
				}
			}
			else if (closestDotC > playerC) {
				if (tie == 0) {
					moveDown();
				}
				else {
					moveRight();
				}
			}
			else {
				moveDown();
			}
		}
		else if (closestDotR == playerR) {
			if (closestDotC < playerC) {
				moveLeft();
			}
			if (closestDotC > playerC) {
				moveRight();
			}
		}	
	}
	public Color getColor(){
		return playerColor;
	}
}
