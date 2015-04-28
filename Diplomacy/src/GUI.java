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
	private int cellSize = 50;
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
		int size = Board.getSize();
		int w = size;
		int h = size;
		g.setColor(Color.BLACK);
		for (int x = 1; x < size; x++){
			g.drawLine(x * cellSize-1, 0, x * cellSize - 1, cellSize * cellSize - 1);
		}
		for (int y=1; y<size; y++){
			g.drawLine(0, y * cellSize-1, cellSize * cellSize-1, y * cellSize-1);
		}
		for (int r=0; r<size; r++){
			for (int c=0; c<size; c++){
				if(b.get(r, c) == 1){
					g.setColor(Color.BLACK);
					g.fillRect(r * cellSize, c*cellSize, cellSize-1, cellSize-1);
				}
				else if(b.get(r,c) == 0){
					g.setColor(Color.WHITE);
					g.fillRect(r*cellSize,  c*cellSize,  cellSize-1, cellSize-1);
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
		guiFrame.setSize(515, 530);
		guiFrame.setLocationRelativeTo(null);

		//GUI g = new GUI();

	
		
		
	}
}
