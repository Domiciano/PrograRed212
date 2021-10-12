package model;

public class Attack {

	private String type = "attack";
	private int f;
	private int c;
	
	public Attack(int f, int c) {
		this.f=f;
		this.c=c;
	}
	
	public Attack() {
		
	}

	public String getType() {
		return type;
	}

	public int getF() {
		return f;
	}

	public int getC() {
		return c;
	}
	
	
	
}
