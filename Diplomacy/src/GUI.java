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
		Board b = new Board();
		
	}
	
	private void constructGUI(){
		JFrame guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Diplomacy");
		guiFrame.setSize(600, 450);
		guiFrame.setLocationRelativeTo(null);
		
		for (int r=0; r<b.getSize(); r++){
			for (int c=0; c<b.getSize(); c++){
				
			}
		}
		
		final JPanel listPanel = new JPanel();
		listPanel.setVisible(true);
		JLabel Lb1 = new JLabel("Diplomacy Label");
		JList boardList = new JList();
		
		
		
	}
	
	public static void main (String[] args){
		GUI runner = new GUI();
	}
}
