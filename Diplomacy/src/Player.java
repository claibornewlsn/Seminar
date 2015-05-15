import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

/**
 * The player class holds all the information for each player and also 
 * has the strategy for each player (both defensive and offensive)
 * @author wilsonc
 *
 */
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
					dotC.add(c);
					
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
	
	
	/**
	 * This is the method called by the board to move the players.  It determines whether the player
	 * should attack based on how close it is to a dot it does not own and how close another player is to a 
	 * dot it does not own.
	 * @param p
	 */
	public void determineOffOrDef(Player p) {
        if (dotDistance(p.closestDotR,p.closestDotC) < p.dotDistance(p.closestDotR,p.closestDotC)) {
               movePlayer(p.closestDotR,p.closestDotC);
               System.out.println("Devensive");
        }
        else {
               movePlayer(closestDotR,closestDotC);
               System.out.println("Offensive");
        }
 }
 

	


  
	private void determineDistances(){
		closestDotDis = dotDistance(closestDotR,closestDotC);
		while (!dotR.isEmpty()){
			//this should be changed such that the closestDotDis is changed when the player is on a dot it owns
			
			if(closestDotDis > dotDistance(dotR.get(0), dotC.get(0))){
				closestDotR = dotR.get(0);
				closestDotC = dotC.get(0);
			}
			dotR.remove(0);
			dotC.remove(0);	
		}
	}
	
	//this is a simple tool used to determine the distance between a player and a dot
	//Players can only move up/down/left/right so the distance is calculated accordingly
	private int dotDistance(int r, int c){
		int ans = Math.abs(playerR-r);
		ans += Math.abs(playerC-c);
		return ans;
	}
	
	//every move, the player's closest dot is updated.  A player's closest dot can only
	//refer to dots it does not already own.  If it owns all dots, it's closest dot is set
	//at the point (0,0)
	public void changeClosestDotLoc(ArrayList<Dot> dt){
		//this is removing elements from the original dot list and needs to be changed.
		ArrayList<Dot> good = new ArrayList<Dot>();
		for(Dot d : dt){
			if(!(d.getPlayer() == playerValue))
				good.add(d);
		}
		
		if(!good.isEmpty()){
			closestDotR = good.get(0).getRow();
			closestDotC = good.get(0).getCol();
			while(!good.isEmpty()){
				if(closestDotR > dotDistance(good.get(0).getRow(), good.get(0).getCol())){
					closestDotR = good.get(0).getRow();
					closestDotC = good.get(0).getCol();
				}
				for(Dot d : good){
					if(closestDotR > dotDistance(d.getRow(), d.getCol())){
						closestDotR = d.getRow();
						closestDotC = d.getCol();
					}
				}
				good.remove(0);
			}

		}
		else{
			//if the player owns all the dots, then go to the spot (0,0)
			closestDotR = 0;
			closestDotC = 0;
		}		
	}
	
	public int getClosestDotR(){
		return closestDotR;
	}
	public int getClosestDotC(){
		return closestDotC;
	}
	
	//this method moves the player based on it's location relative to it's closest dot
	//the "tie" is used to decide weather to move vertically or horizontally when there is a choice
    private void movePlayer(int dotR, int dotC) {
        int tie = (int) (Math.random()*2);
        System.out.println("dot R and C: " + dotR + " , " + dotC + "........ Player R/C " + playerR + " , " + playerC);
        if (dotR < playerR) {
               if (dotC < playerC) {
                     if (tie == 0) {
                            playerC--;
                     }
                     else {
                            playerR--;
                     }
               }
               else if (dotC > playerC) {
                     if (tie == 0) {
                            playerC--;
                     }
                     else {
                            playerR++;
                     }
               }
               else {
                     playerR--;
               }
        }
        else if (dotR > playerR) {
               if (dotC < playerC) {
                     if (tie == 0) {
                            playerR++;
                     }
                     else {
                            playerC--;
                     }
               }
               else if (dotC > playerC) {
                     if (tie == 0) {
                            playerR++;
                     }
                     else {
                            playerC++;
                     }
               }
               else {
                     playerR++;
               }
        }
        else if (dotR == playerR) {
               if (dotC < playerC) {
                     playerC--;
               }
               if (dotC > playerC) {
                     playerC++;
               }
        }      
 }
}


	
	
	