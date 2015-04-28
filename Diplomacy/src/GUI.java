import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JComboBox; 
import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JList; 

import java.awt.BorderLayout; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;


/**
 * @author wilsonc
 * Integers have been changed to colors to make implimentation 
 * of a GUI easier.  White represents a blank space, black is a 
 * dot, and the players are 2 different colors
 *
 */

public class GUI extends JPanel{
	private Color[][] board;
	//private Player p1;
	//private Player p2;
	private Board b;
	private int size = Board.getSize();
	private int cellSize = 150;
	private int cellUnderMouse;
	
	public GUI() {
		
		
		constructGUI();
		b = new Board();
		
	}
	
	private void constructGUI(){
		JFrame guiFrame = new JFrame();
		final JPanel listPanel = new JPanel();
		listPanel.setVisible(true);
		JLabel Lb1 = new JLabel("Diplomacy Label");
		JList<Integer> boardList = new JList<Integer>();
		
		
		for (int r=0; r<Board.getSize(); r++){
			for (int c=0; c<Board.getSize(); c++){
				//boardList.add(b.get(r, c));
			}
		}
		
		
		
	}
	
	public void paint (Graphics g){
		super.paintComponent(g);
		int size = b.getSize();
		int w = size;
		int h = size;
		g.setColor(Color.WHITE);
		for (int x = 1; x < size; x++){
			g.drawLine(x * size-1, 0, x * size - 1, size * size - 1);
		}
		for (int y=1; y<size; y++){
			g.drawLine(0, y * size-1, size * size-1, y * size-1);
		}
		for (int r=0; r<size; r++){
			for (int c=0; c<size; c++){
				if(b.get(r, c) == 1){
					g.setColor(Color.BLACK);
					g.fillRect(r * size, c*size, size-1, size-1);
				}
				else if(b.get(r,c) == 0){
					g.setColor(Color.WHITE);
					g.fillRect(r*size,  c*size,  size-1, size-1);
				}
					
			}
		}
	}
	
	public void mousePressed(MouseEvent e){
		saveCellUnderMouse(e.getX(), e.getY());
	}
	
	public void saveCellUnderMouse(int x, int y){
		try{
			cellUnderMouse = b.get(x/cellSize, y/cellSize);
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e){
			
		}
	}
	
	public static void main (String[] args){
		JFrame guiFrame = new JFrame();
		guiFrame.add(new GUI());
		guiFrame.setVisible(true);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Diplomacy");
		guiFrame.setSize(600, 450);
		guiFrame.setLocationRelativeTo(null);

		//GUI g = new GUI();

	
		
		
	}
}
