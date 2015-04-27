import java.awt.Color;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JComboBox; 
import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JList; 
import java.awt.BorderLayout; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;


/**
 * @author wilsonc
 * Integers have been changed to colors to make implimentation 
 * of a GUI easier.  White represents a blank space, black is a 
 * dot, and the players are 2 different colors
 *
 */

public class GUI {
	private Color[][] board;
	private Player p1;
	private Player p2;
	private int[][] b;
	private int size = Board.getSize();
	
	public GUI(){
		constructGUI();
		p1 = new Player(2, Color.RED);
		p2 = new Player(3, Color.BLUE);
		board = Board.getBoard();
		//board = new Color[size][size];
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board.length; c++) {
				if (b[r][c] == 0){
					board[r][c] = Color.WHITE;
				}
				else if (b[r][c] == 1) {
					board[r][c] = Color.BLACK;
				}
				else if(b[r][c] == 2){		//2 and 3 should be changed to the player number field.
					board[r][c] = p1.getColor();
				}
				else if(b[r][c] == 3){
					board [r][c] = p2.getColor();	
				}		
			}
		}
		
		
		
		
	}
	
	private void constructGUI(){
		JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Diplomacy");
		guiFrame.setSize(600, 450);
		guiFrame.setLocationRelativeTo(null);
		
	}
	
	public static void main (String[] args){
		GUI runner = new GUI();
	}
}
