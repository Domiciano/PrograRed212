package model;

public class Attack {

	public String type="Attack";
	private int fil;
	private int col;
	
	public Attack() {}
	public Attack(int fil, int col) {
		this.fil = fil;
		this.col = col;
	}
	public String getType() {
		return type;
	}
	public int getFil() {
		return fil;
	}
	public int getCol() {
		return col;
	}
	
}
