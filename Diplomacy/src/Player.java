import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;


public class Player {

	private int playerR;
	private int playerC;

	private Color playerColor;
	private int playerValue;
	//private int[][] board;
	// *** Offensive Strategy ***
	private int closestDotR;
	private int closestDotC;

	private int closestDotDis;
	private ArrayList<Integer> dotR = new ArrayList<Integer>();
	private ArrayList<Integer> dotC = new ArrayList<Integer>();
	
	

	
	
	public Player(int val, int row, int col) {
		playerValue = val;
		playerR = row;
		playerC = col;
		//makeOffense();
		//WE SHOULD MAKE THE OFFENSIVE STRATEGY IN THE BOARD CLASS
	}
	
	public int getRow(){
		return playerR;
	}
	
	public int getCol(){
		return playerC;
	}
	
	public Color getColor(){
		return playerColor;
	}
	public int getVal(){
		return playerValue;
	}
	
	public void setRow(int r){
		playerR = r;
	}
	public void setCol(int c){
		playerC = c;
	}
	
	/**
	 * This method returns an array representing the row and col values.  The array should be in
	 * the form [rowValue][colValue]
	 * @param board
	 * @param SIZE
	 * @return
	 */
	public int[] moveOffense(int[][] board, int SIZE){
		for(int r = 0; r<SIZE; r++){
			for (int c = 0; c < SIZE; c++){
				if(board[r][c] == 1){
					dotR.add(r);
					System.out.println("dot R: " + dotR.toString());
					dotC.add(c);
					System.out.println("Dot C: " + dotC.toString());
				}
			}
		}
		//set the closest R and C values:
		determineDistances();
		int[] temp = new int[2];
		temp[0] = closestDotR;
		temp[1] = closestDotC;
		return temp;
	}
	
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
	
	private void determineDistances(){
		closestDotR = dotR.get(0);
		closestDotC = dotC.get(0);
		closestDotDis = dotDistance(closestDotR,closestDotC);
		while (!dotR.isEmpty()){
			if(closestDotDis > dotDistance(dotR.get(0), dotC.get(0))){
				closestDotR = dotR.get(0);
				closestDotC = dotC.get(0);
			}
			dotR.remove(0);
			dotC.remove(0);
				
		}
		System.out.println("completed 'determineDistances'");
		
	}
	
	private int dotDistance(int r, int c){
		int ans = Math.abs(playerR-r);
		ans += Math.abs(playerC-c);
		return ans;
	}
	
	// *** Offensive Strategy ***
//	private void makeOffense() {
//		int num = 0;
//		for (int r = 0; r < Board.getSize(); r++) {
//			for (int c = 0; c < Board.getSize(); c++) {
//				if (board[r][c] == playerValue) {
//					playerR = r;
//					playerC = c;
//				}
//				if (board[r][c] == 1) {
//					dotR.add(r);
//					dotC.add(c);
//				}
//			}
//		}
//		findClosestDot();
//	}
	
//	private void findClosestDot() {
//		closestDotR = dotR.poll();
//		closestDotC = dotC.poll();
//		closestDotDis = dotDistance(closestDotR,closestDotC);
//		while (!dotR.isEmpty() || !dotC.isEmpty()) {
//			if (closestDotDis > dotDistance(dotR.peek(),dotC.peek())) {
//				closestDotR = dotR.poll();
//				closestDotC = dotC.poll();
//				closestDotDis = dotDistance(closestDotR,closestDotC);
//			}
//			else{
//				dotR.poll();
//				dotC.poll();
//			}
//
//		}
//	}
	
//	private int dotDistance(int r, int c) {
//		int ans = Math.abs(playerR-r);
//		ans += Math.abs(playerC-c);
//		return ans;
//	}
	// ***************************************
	//PLAYERS SHOULD ALSO MOVE IN THE BOARD CLASS
	
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
	
//	public Color[][] getBoard() {
//		return board;
//	}
	
	// moves the player using the offensive strategy
//	public void movePlayerOff() {
//		int tie = (int) (Math.random()*2);
//		if (closestDotR < playerR) {
//			if (closestDotC < playerC) {
//				if (tie == 0) {
//					moveUp();
//				}
//				else {
//					moveLeft();
//				}
//			}
//			else if (closestDotC > playerC) {
//				if (tie == 0) {
//					moveUp();
//				}
//				else {
//					moveRight();
//				}
//			}
//			else {
//				moveUp();
//			}
//		}
//		else if (closestDotR > playerR) {
//			if (closestDotC < playerC) {
//				if (tie == 0) {
//					moveDown();
//				}
//				else {
//					moveLeft();
//				}
//			}
//			else if (closestDotC > playerC) {
//				if (tie == 0) {
//					moveDown();
//				}
//				else {
//					moveRight();
//				}
//			}
//			else {
//				moveDown();
//			}
//		}
//		else if (closestDotR == playerR) {
//			if (closestDotC < playerC) {
//				moveLeft();
//			}
//			if (closestDotC > playerC) {
//				moveRight();
//			}
//		}	
//	}

}
