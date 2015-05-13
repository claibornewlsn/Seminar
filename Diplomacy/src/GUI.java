import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JComboBox; 
import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JList; 
import javax.swing.Timer;

import java.awt.BorderLayout; 
import java.awt.event.*; 



/**
 * @author wilsonc
 * Integers have been changed to colors to make implimentation 
 * of a GUI easier.  White represents a blank space, black is a 
 * dot, and the players are 2 different colors
 *
 */

public class GUI extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	//private Color[][] board;
	//private Player p1;
	//private Player p2;
	private Board b;
	private int size = Board.getSize();
	private int cellSize = 50;
	private int cellUnderMouse;
	private Timer t;
	private int timeStep;
	private boolean isRunning;
	private JButton next;
	
	public GUI(Board board) { 
		
		constructGUI();
		b = board;
		t = new Timer(5000, this);
		//t.setDelay(5000);
		next = new JButton("Next");
		next.setVisible(true);
		next.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						b.move();
						b.replaceDots();
						next.setVisible(true);
						repaint();
						System.out.println("END OF MOVE \n \n");
					}
				}
				);
		this.add(next);
		this.validate();
	}
	


	public void stop(){
		t.stop();
		isRunning = false;
	}
	
	public void start(){
		t.start();
		isRunning = true;
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	public void startStopButtonClicked() {
		if ( isRunning() ) 
			stop();
		else 
		    start();
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
				else if (b.get(r,  c) == 2){
					g.setColor(Color.RED);
					g.fillRect(r*cellSize,  c*cellSize,  cellSize-1, cellSize-1);
				}
				else if (b.get(r, c) == 3){
					g.setColor(Color.BLUE);
					g.fillRect(r*cellSize,  c*cellSize,  cellSize-1, cellSize-1);
				}
					
			}
		}
	} 
	 
	public void mousePressed(MouseEvent e){
		saveCellUnderMouse(e.getX(), e.getY());
		System.out.println("Mouse at: " + e.getX() + " , " + e.getY());
	}
	
	public void saveCellUnderMouse(int x, int y){
		try{
			cellUnderMouse = b.get(x/cellSize, y/cellSize);
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e){	
		}
	}
	
	

	public void actionPerformed(ActionEvent e) {
		System.out.println("called it");
		b.move();
		repaint();
		
	}

	public void draw(int x, int y){
		try{
			b.setCell(x / cellSize, y/cellSize, 1-cellUnderMouse);
			repaint();
		}
		catch (java.lang.ArrayIndexOutOfBoundsException e){
			//ignore
		}
		finally{
			
		}
	}



	public void mouseDragged(MouseEvent e) {
		draw(e.getX(), e.getY());
		
	}
	public void mouseMoved(MouseEvent e) {
		//nothing
		
	}
	public void mouseClicked(MouseEvent e) {
		saveCellUnderMouse(e.getX(), e.getY());
		System.out.println("called");
		
	}


	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main (String[] args){
		JFrame guiFrame = new JFrame();
		Board b = new Board();
		guiFrame.add(new GUI(b));
		guiFrame.setVisible(true);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Diplomacy");
		guiFrame.setSize((int)(b.getSize() * 51.5),(int)(b.getSize() * 53));
		guiFrame.setLocationRelativeTo(null);
		

	}
	
}
