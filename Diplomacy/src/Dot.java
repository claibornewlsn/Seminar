
public class Dot {

	private int row;
	private int col;
	private int pOwned;
	
	public Dot(int r, int c, int p) {
		row = r;
		col = c; 
		pOwned = p;		
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getPlayer() {
		return pOwned;
	}
	
	public void setPlayer(int p){
		pOwned = p;
	}
	
}