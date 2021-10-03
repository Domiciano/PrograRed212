package model;

public class Enemy {

	public String type = "Enemy";
	private String userName;
	
	public Enemy(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}